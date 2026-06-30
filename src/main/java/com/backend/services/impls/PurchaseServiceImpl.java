package com.backend.services.impls;

import com.backend.mappers.purchase.ProductMapper;
import com.backend.mappers.purchase.PurchaseMapper;
import com.backend.mappers.purchase.PurchaseProductsMapper;
import com.backend.modals.leadger.Ledger;
import com.backend.modals.leadger.LedgerTransaction;
import com.backend.modals.TransactionType;
import com.backend.modals.dto.response.PaginatedResponse;
import com.backend.modals.purchase.Product;
import com.backend.modals.purchase.Purchase;
import com.backend.modals.purchase.PurchaseProducts;
import com.backend.modals.purchase.dto.ProductRequest;
import com.backend.modals.purchase.dto.PurchaseProductRequest;
import com.backend.modals.purchase.dto.PurchaseRequest;
import com.backend.modals.supplier.Supplier;
import com.backend.repositories.LedgerRepository;
import com.backend.repositories.LedgerTransactionRepository;
import com.backend.repositories.purchase.ProductRepository;
import com.backend.repositories.purchase.PurchaseRepository;
import com.backend.repositories.supplier.SupplierRepository;
import com.backend.services.PurchaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final PurchaseMapper purchaseMapper;
    private final SupplierRepository supplierRepository;
    private final PurchaseProductsMapper purchaseProductsMapper;
    private final ProductMapper productMapper;
    private final LedgerRepository ledgerRepository;
    private final LedgerTransactionRepository ledgerTransactionRepository;
    private final PaginationResponseImpl paginationResponse;

    @Transactional
    @Override
    public Boolean createPurchase(PurchaseRequest request) {
        Purchase purchase = purchaseMapper.toEntity(request);
        purchase.setInvoiceNumber(generateInvoiceNumber());
        Supplier supplier = supplierRepository.findById(request.getSupplier().getId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        purchase.setSupplier(supplier);

        for (PurchaseProducts purchaseProduct : purchase.getPurchaseProducts()) {

            Product dtoProduct = purchaseProduct.getProduct();

            Product product = productRepository
                    .findByNameAndCategory(
                            dtoProduct.getName(),
                            dtoProduct.getCategory())
                    .orElseGet(() -> productRepository.save(dtoProduct));

            purchaseProduct.setProduct(product);

            if (product.getQuantity() == null)
                product.setQuantity(0);

            product.setQuantity(product.getQuantity() + purchaseProduct.getPurchaseQuantity());
        }

        Purchase savedPurchase = purchaseRepository.save(purchase);

        createTransactions(request, savedPurchase);

        return true;
    }

    public void createTransactions(PurchaseRequest request, Purchase purchase) {
        // Create or update ledger for the supplier
        Supplier supplier = purchase.getSupplier();
        Ledger ledger = ledgerRepository.findBySupplier(supplier)
                .orElseGet(() -> {
                    Ledger newLedger = new Ledger();
                    newLedger.setSupplier(supplier);
                    newLedger.setAccountCode("SUPPLIER_" + supplier.getId());
                    newLedger.setAccountName(supplier.getName());
                    newLedger.setTotalAmount(BigDecimal.ZERO);
                    newLedger.setDebitAmount(BigDecimal.ZERO);
                    newLedger.setCreditAmount(BigDecimal.ZERO);
                    newLedger.setRemainingBalance(BigDecimal.ZERO);
                    newLedger.setActive(true);
                    return ledgerRepository.save(newLedger);
                });

        // Update ledger amounts
        ledger.setTotalAmount(ledger.getTotalAmount().add(request.getTotal()));
        ledger.setDebitAmount(ledger.getDebitAmount().add(request.getPaidAmount()));
        ledger.setRemainingBalance(request.getDueAmount());
        ledgerRepository.save(ledger);

        if (request.getPaidAmount().compareTo(BigDecimal.ZERO) > 0) {
            // Create a new ledger transaction for the paid amount
            LedgerTransaction transaction = new LedgerTransaction();
            transaction.setLedger(ledger);
            transaction.setTransactionDate(purchase.getDate());
            transaction.setReferenceNo(purchase.getInvoiceNumber());
            transaction.setTransactionType(TransactionType.PURCHASE);
            transaction.setDebitAmount(request.getPaidAmount());
            transaction.setCreditAmount(BigDecimal.ZERO);
            transaction.setBalanceAfterTransaction(ledger.getTotalAmount());
            transaction.setPurchase(purchase);
            transaction.setRemarks("Purchase payment for invoice: " + purchase.getInvoiceNumber());
            ledgerTransactionRepository.save(transaction);
        }
    }

    @Transactional
    @Override
    public Boolean updatePurchase(PurchaseRequest request) {

        Purchase purchase = purchaseRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Purchase not found"));

        if (purchase.getSupplier().getLedger() != null) {
            Ledger ledger = purchase.getSupplier().getLedger();

            // Reverse the previous ledger amounts
            ledger.setTotalAmount(ledger.getTotalAmount().subtract(purchase.getTotal()));
            ledger.setDebitAmount(ledger.getDebitAmount().subtract(purchase.getPaidAmount()));
            ledger.setRemainingBalance(ledger.getRemainingBalance().subtract(purchase.getDueAmount()));
            ledgerRepository.save(ledger);

            // Remove the previous transaction related to this purchase
            if (purchase.getLedgerTransactions() != null)
                ledgerTransactionRepository.delete(purchase.getLedgerTransactions());

        }

        // Reverse stock
        for (PurchaseProducts oldPurchaseProduct : purchase.getPurchaseProducts()) {

            Product oldProduct = oldPurchaseProduct.getProduct();

            oldProduct.setQuantity(
                    oldProduct.getQuantity() - oldPurchaseProduct.getPurchaseQuantity()
            );
        }

        // Update basic fields
        purchaseMapper.update(request, purchase);

        // Update supplier
        Supplier supplier = supplierRepository.findById(request.getSupplier().getId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        purchase.setSupplier(supplier);

        // Remove old children
        purchase.getPurchaseProducts().clear();

        // Add new children
        for (PurchaseProductRequest dto : request.getPurchaseProducts()) {

            PurchaseProducts purchaseProduct = purchaseProductsMapper.toEntity(dto);

            ProductRequest productDto = dto.getProduct();

            Product product = productRepository
                    .findByNameAndCategory(
                            productDto.getName(),
                            productDto.getCategory())
                    .orElseGet(() -> {
                        Product p = productMapper.toEntity(productDto);
                        p.setQuantity(0);
                        return productRepository.save(p);
                    });

            product.setQuantity(
                    product.getQuantity() + dto.getPurchaseQuantity()
            );

            purchaseProduct.setProduct(product);
            purchaseProduct.setPurchase(purchase);

            purchase.getPurchaseProducts().add(purchaseProduct);
        }

        purchaseRepository.save(purchase);

        createTransactions(request, purchase);
        return true;
    }

    @Override
    public PurchaseRequest getPurchaseById(UUID purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new RuntimeException("Purchase Not Found"));

        System.out.println("Supplier ID: " + purchase.getSupplier().getId());
        System.out.println("Supplier Name: " + purchase.getSupplier().getName());
        System.out.println("Company: " + purchase.getSupplier().getCompanyName());

        return purchaseMapper.toDto(purchase);
    }

    @Override
    public PaginatedResponse<PurchaseRequest> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Purchase> purchasePage = purchaseRepository.findAll(pageable);
        return paginationResponse.buildPaginatedResponse(purchasePage.stream().map(purchaseMapper::toDto).toList(), purchasePage);
    }

    @Override
    public PaginatedResponse<PurchaseRequest> getAllBySupplierId(UUID supplierId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Purchase> purchasePage = purchaseRepository.findBySupplierId(supplierId, pageable);
        return paginationResponse.buildPaginatedResponse(purchasePage.stream().map(purchaseMapper::toDto).toList(),purchasePage);
    }



    @Override
    public PaginatedResponse<PurchaseRequest> getAllByProductId(UUID productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Purchase> purchasePage = purchaseRepository.findPurchasesByProductId(productId, pageable);
        return paginationResponse.buildPaginatedResponse(purchasePage.stream().map(purchaseMapper::toDto).toList(), purchasePage);
    }

    public String generateInvoiceNumber() {

        String date = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String prefix = "INV-" + date + "-";

        Optional<Purchase> lastInvoice = purchaseRepository
                .findTopByInvoiceNumberStartingWithOrderByInvoiceNumberDesc(prefix);

        int nextSequence = 1;

        if (lastInvoice.isPresent()) {
            String lastInvoiceNumber = lastInvoice.get().getInvoiceNumber();

            nextSequence = Integer.parseInt(lastInvoiceNumber.substring(prefix.length())) + 1;
        }

        return prefix + nextSequence;
    }
}

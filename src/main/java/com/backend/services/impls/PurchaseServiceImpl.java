package com.backend.services.impls;

import com.backend.mappers.purchase.ProductMapper;
import com.backend.mappers.purchase.PurchaseMapper;
import com.backend.mappers.purchase.PurchaseProductsMapper;
import com.backend.modals.dto.response.PaginatedResponse;
import com.backend.modals.purchase.Product;
import com.backend.modals.purchase.Purchase;
import com.backend.modals.purchase.PurchaseProducts;
import com.backend.modals.purchase.dto.ProductRequest;
import com.backend.modals.purchase.dto.PurchaseProductRequest;
import com.backend.modals.purchase.dto.PurchaseRequest;
import com.backend.modals.supplier.Supplier;
import com.backend.repositories.purchase.ProductRepository;
import com.backend.repositories.purchase.PurchaseProductRepository;
import com.backend.repositories.purchase.PurchaseRepository;
import com.backend.repositories.supplier.SupplierRepository;
import com.backend.services.PurchaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    private final PurchaseProductRepository purchaseProductRepository;

    @Override
    public Boolean createPurchase(PurchaseRequest request) {
        Purchase purchase = purchaseMapper.toEntity(request);
        purchase.setSupplier(supplierRepository.findById(request.getSupplier().getId())
                .orElseThrow(() -> new RuntimeException("Supplier not found")));

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


            product.setQuantity(
                    product.getQuantity() + purchaseProduct.getPurchaseQuantity()
            );
        }

        purchaseRepository.save(purchase);

        return true;
    }


    @Transactional
    @Override
    public Boolean updatePurchase(PurchaseRequest request) {

        Purchase purchase = purchaseRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Purchase not found"));

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
        return null;
    }
}

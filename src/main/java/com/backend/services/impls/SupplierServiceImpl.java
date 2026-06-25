package com.backend.services.impls;

import com.backend.mappers.supplier.SupplierBankDetailsMapper;
import com.backend.mappers.supplier.SupplierContactDetailsMapper;
import com.backend.mappers.supplier.SupplierGstInformationMapper;
import com.backend.mappers.supplier.SupplierMapper;
import com.backend.modals.supplier.Supplier;
import com.backend.modals.dto.request.SupplierRequest;
import com.backend.modals.dto.response.PaginatedResponse;
import com.backend.repositories.supplier.SupplierRepository;
import com.backend.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final PaginationResponseImpl paginationResponse;
    private final SupplierBankDetailsMapper supplierBankDetailsMapper;
    private final SupplierContactDetailsMapper supplierContactDetailsMapper;
    private final SupplierGstInformationMapper supplierGstInformationMapper;


    @Override
    public Boolean createSupplier(SupplierRequest supplierRequest) {
        supplierRepository.save(supplierMapper.toEntity(supplierRequest));
        return true;
    }

    @Override
    public Boolean updateSupplier(SupplierRequest supplierRequest) {
        Supplier supplier = supplierRepository.findById(supplierRequest.getId()).orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplierMapper.update(supplierRequest, supplier);

        supplierRepository.save(supplier);
        return true;
    }


    @Override
    public PaginatedResponse<SupplierRequest> getSuppliers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
        return paginationResponse.buildPaginatedResponse(
                supplierPage.getContent()
                        .stream()
                        .map(supplierMapper::toDto)
                        .toList(),
                supplierPage);
    }

    @Override
    public SupplierRequest getSupplierById(UUID id) {
        return supplierRepository.findById(id)
                .map(supplierMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    @Override
    public Boolean deleteSupplier(UUID id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

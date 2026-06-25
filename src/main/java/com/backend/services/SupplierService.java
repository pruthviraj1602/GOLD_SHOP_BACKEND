package com.backend.services;

import com.backend.modals.dto.request.SupplierRequest;
import com.backend.modals.dto.response.PaginatedResponse;

import java.util.UUID;

public interface SupplierService {


    Boolean createSupplier(SupplierRequest supplierRequest);


    Boolean updateSupplier(SupplierRequest supplierRequest);

    PaginatedResponse<SupplierRequest> getSuppliers(int page, int size);

    SupplierRequest getSupplierById(UUID id);

    Boolean deleteSupplier(UUID id);

}

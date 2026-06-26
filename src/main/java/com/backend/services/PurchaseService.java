package com.backend.services;

import com.backend.modals.dto.response.PaginatedResponse;
import com.backend.modals.purchase.dto.PurchaseRequest;

import java.util.UUID;

public interface PurchaseService {

    Boolean createPurchase(PurchaseRequest request);

    Boolean updatePurchase(PurchaseRequest request);

    PurchaseRequest getPurchaseById(UUID purchaseId);

    PaginatedResponse<PurchaseRequest> getAll(int page , int size);

    PaginatedResponse<PurchaseRequest> getAllBySupplierId(UUID supplierId, int page , int size);
}

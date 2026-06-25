package com.backend.modals.purchase.dto;

import com.backend.modals.dto.request.SupplierRequest;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class PurchaseRequest {

    private UUID id;

    private Date date;

    private String invoiceNumber;

    private BigDecimal subTotal;

    private BigDecimal gstSlabAmount;

    private BigDecimal total;

    private List<PurchaseProductRequest> purchaseProducts;

    private SupplierRequest supplier;
}

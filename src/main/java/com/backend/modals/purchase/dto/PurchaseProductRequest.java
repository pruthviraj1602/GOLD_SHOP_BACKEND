package com.backend.modals.purchase.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PurchaseProductRequest {
    private UUID id;

    private Float weight;

    private Float stoneWeight;

    private Float netWeight;

    private BigDecimal rate;

    private BigDecimal makingCharges;

    private BigDecimal stoneValue;

    private String gstSlab;

    private Integer purchaseQuantity;

    private BigDecimal sellingPrice;

    private ProductRequest product;
}

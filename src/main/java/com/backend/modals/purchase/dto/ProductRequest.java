package com.backend.modals.purchase.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String id;

    private String name;

    private String category;

    private String metalPurity;

    private Integer quantity;
}

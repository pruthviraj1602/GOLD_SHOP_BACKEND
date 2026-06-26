package com.backend.services;

import com.backend.modals.purchase.dto.ProductRequest;

import java.util.List;

public interface ProductService {


    List<ProductRequest> searchProduct(String keyword);
}

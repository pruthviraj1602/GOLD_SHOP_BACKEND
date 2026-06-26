package com.backend.controllers;

import com.backend.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping("/search")
    public ResponseEntity<?> searchProduct(String keyword) {
        return ResponseEntity.ok(productService.searchProduct(keyword));
    }
}

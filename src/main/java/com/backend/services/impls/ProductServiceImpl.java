package com.backend.services.impls;

import com.backend.mappers.purchase.ProductMapper;
import com.backend.modals.purchase.dto.ProductRequest;
import com.backend.repositories.purchase.ProductRepository;
import com.backend.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductRequest> searchProduct(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

}

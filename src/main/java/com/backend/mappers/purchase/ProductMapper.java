package com.backend.mappers.purchase;

import com.backend.modals.purchase.Product;
import com.backend.modals.purchase.dto.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequest request);

    ProductRequest toDto(Product product);

    void update(ProductRequest request,
                @MappingTarget Product product);
}

package com.backend.mappers.purchase;

import com.backend.modals.purchase.PurchaseProducts;
import com.backend.modals.purchase.dto.PurchaseProductRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface PurchaseProductsMapper {
    PurchaseProducts toEntity(PurchaseProductRequest request);

    PurchaseProductRequest toDto(PurchaseProducts entity);
}

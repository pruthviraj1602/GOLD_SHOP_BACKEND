package com.backend.mappers.purchase;

import com.backend.mappers.supplier.SupplierMapper;
import com.backend.modals.purchase.Purchase;
import com.backend.modals.purchase.dto.PurchaseRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = {
                PurchaseProductsMapper.class,
                SupplierMapper.class
        }
)
public interface PurchaseMapper {

    Purchase toEntity(PurchaseRequest request);

    PurchaseRequest toDto(Purchase purchase);

    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "purchaseProducts", ignore = true)
    void update(PurchaseRequest request,
                @MappingTarget Purchase purchase);

    @AfterMapping
    default void linkChildren(@MappingTarget Purchase purchase) {

        if (purchase.getPurchaseProducts() != null) {

            purchase.getPurchaseProducts()
                    .forEach(product -> product.setPurchase(purchase));
        }
    }
}

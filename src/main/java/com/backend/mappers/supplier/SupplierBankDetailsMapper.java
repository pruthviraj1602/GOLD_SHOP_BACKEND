package com.backend.mappers.supplier;

import com.backend.modals.supplier.SupplierBankDetails;
import com.backend.modals.dto.request.SupplierBankDetailsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SupplierBankDetailsMapper {

    @Mapping(target = "supplier", ignore = true)
    SupplierBankDetails toEntity(SupplierBankDetailsRequest request);

    SupplierBankDetailsRequest toDto(SupplierBankDetails entity);

    @Mapping(target = "supplier", ignore = true)
    void update(SupplierBankDetailsRequest request,
                @MappingTarget SupplierBankDetails entity);
}

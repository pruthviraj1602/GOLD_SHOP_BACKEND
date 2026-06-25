package com.backend.mappers.supplier;

import com.backend.modals.supplier.SupplierContactDetails;
import com.backend.modals.dto.request.SupplierContactDetailsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SupplierContactDetailsMapper {

    @Mapping(target = "supplier", ignore = true)
    SupplierContactDetails toEntity(SupplierContactDetailsRequest request);

    SupplierContactDetailsRequest toDto(SupplierContactDetails entity);

    @Mapping(target = "supplier", ignore = true)
    void update(SupplierContactDetailsRequest request,
                @MappingTarget SupplierContactDetails entity);
}

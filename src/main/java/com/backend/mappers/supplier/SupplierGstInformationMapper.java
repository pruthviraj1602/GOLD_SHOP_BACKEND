package com.backend.mappers.supplier;

import com.backend.modals.supplier.SupplierGstInformation;
import com.backend.modals.dto.request.SupplierGstInformationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SupplierGstInformationMapper {

    @Mapping(target = "supplier", ignore = true)
    SupplierGstInformation toEntity(SupplierGstInformationRequest request);

    SupplierGstInformationRequest toDto(SupplierGstInformation entity);

    @Mapping(target = "supplier", ignore = true)
    void update(SupplierGstInformationRequest request,
                @MappingTarget SupplierGstInformation entity);
}

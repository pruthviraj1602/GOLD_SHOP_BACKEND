package com.backend.mappers.supplier;

import com.backend.mappers.ledger.LedgerMapper;
import com.backend.modals.supplier.Supplier;
import com.backend.modals.dto.request.SupplierRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = {
                SupplierBankDetailsMapper.class,
                SupplierContactDetailsMapper.class,
                SupplierGstInformationMapper.class,
                LedgerMapper.class
        }
)
public interface SupplierMapper {

    @Mapping(source = "bankDetails", target = "supplierBankDetails")
    @Mapping(source = "contactDetails", target = "supplierContactDetails")
    @Mapping(source = "gstInformation", target = "supplierGstInformation")
    Supplier toEntity(SupplierRequest request);

    @Mapping(source = "ledger", target = "ledger")
    @Mapping(source = "supplierBankDetails", target = "bankDetails")
    @Mapping(source = "supplierContactDetails", target = "contactDetails")
    @Mapping(source = "supplierGstInformation", target = "gstInformation")
    SupplierRequest toDto(Supplier supplier);

    @Mapping(source = "bankDetails", target = "supplierBankDetails")
    @Mapping(source = "contactDetails", target = "supplierContactDetails")
    @Mapping(source = "gstInformation", target = "supplierGstInformation")
    void update(SupplierRequest request,
                @MappingTarget Supplier supplier);

    @AfterMapping
    default void linkChildren(@MappingTarget Supplier supplier) {

        if (supplier.getSupplierBankDetails() != null) {
            supplier.getSupplierBankDetails().setSupplier(supplier);
        }

        if (supplier.getSupplierContactDetails() != null) {
            supplier.getSupplierContactDetails().setSupplier(supplier);
        }

        if (supplier.getSupplierGstInformation() != null) {
            supplier.getSupplierGstInformation().setSupplier(supplier);
        }
    }
}

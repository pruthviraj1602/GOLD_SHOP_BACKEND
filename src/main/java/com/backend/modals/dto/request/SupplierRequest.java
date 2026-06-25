package com.backend.modals.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class SupplierRequest {

    private UUID id;

    private String name;

    private String companyName;

    private String supplierType;

    private String taxAddress;

    private String registeredBillingAddress;

    private String shippingWarehouseAddress;

    private String paymentTerms;

    private String status;

    private SupplierGstInformationRequest gstInformation;

    private SupplierBankDetailsRequest bankDetails;

    private SupplierContactDetailsRequest contactDetails;
}

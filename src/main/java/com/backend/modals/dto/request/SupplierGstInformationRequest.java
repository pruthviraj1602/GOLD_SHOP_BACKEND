package com.backend.modals.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class SupplierGstInformationRequest {
    private UUID id;

    private String gstNumber;

    private String stateName;

    private String stateCode;

    private String gstType;
}

package com.backend.modals.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class SupplierBankDetailsRequest {
    private UUID id;

    private String bankAccountNumber;

    private String bankName;

    private String ifscCode;

    private String branchName;

    private String accountHolderName;
}

package com.backend.modals.leadger.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class LedgerResponse {
    private UUID id;

    private String accountCode;

    private String accountName;

    private BigDecimal totalAmount;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    private BigDecimal remainingBalance;

    private Boolean active;

    private List<LedgerTransactionResponse> ledgerTransactionResponses;
}

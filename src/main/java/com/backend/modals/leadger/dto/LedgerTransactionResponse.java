package com.backend.modals.leadger.dto;

import com.backend.modals.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
public class LedgerTransactionResponse {
    private UUID id;

    private Date transactionDate;

    private String referenceNo;

    private TransactionType transactionType;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    private BigDecimal balanceAfterTransaction;

    private String remarks;
}

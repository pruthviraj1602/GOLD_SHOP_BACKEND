package com.backend.modals;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LedgerTransaction extends BaseAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String transactionDate;

    private String referenceNo;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    private BigDecimal balanceAfterTransaction;

    private String remarks;

    @ManyToOne
    @JoinColumn(name = "ledger_id")
    private Ledger ledger;

}

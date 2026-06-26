package com.backend.mappers.ledger;

import com.backend.modals.leadger.LedgerTransaction;
import com.backend.modals.leadger.dto.LedgerTransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LedgerTransactionMapper {

    LedgerTransactionResponse toDto(LedgerTransaction ledgerTransaction);
}

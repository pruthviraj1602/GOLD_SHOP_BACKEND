package com.backend.mappers.ledger;

import com.backend.modals.leadger.Ledger;
import com.backend.modals.leadger.dto.LedgerResponse;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {
                LedgerTransactionMapper.class
        }
)
public interface LedgerMapper {
    @Mapping(source = "transactions", target = "ledgerTransactionResponses")
    LedgerResponse toDto(Ledger ledger);
}

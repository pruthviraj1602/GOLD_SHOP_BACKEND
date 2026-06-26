package com.backend.repositories;

import com.backend.modals.leadger.Ledger;
import com.backend.modals.leadger.LedgerTransaction;
import com.backend.modals.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LedgerTransactionRepository extends JpaRepository<LedgerTransaction, UUID> {
}

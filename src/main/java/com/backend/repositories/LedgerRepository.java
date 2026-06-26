package com.backend.repositories;

import com.backend.modals.leadger.Ledger;
import com.backend.modals.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, UUID> {
    Optional<Ledger> findBySupplier(Supplier supplier);
}

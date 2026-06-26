package com.backend.repositories.purchase;

import com.backend.modals.purchase.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase , UUID> {
    Optional<Purchase> findTopByInvoiceNumberStartingWithOrderByInvoiceNumberDesc(String prefix);

    Page<Purchase> findBySupplierId(UUID supplierId, Pageable pageable);
}

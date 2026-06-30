package com.backend.repositories.purchase;

import com.backend.modals.purchase.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase , UUID> {
    Optional<Purchase> findTopByInvoiceNumberStartingWithOrderByInvoiceNumberDesc(String prefix);

    Page<Purchase> findBySupplierId(UUID supplierId, Pageable pageable);

    @Query("""
            SELECT DISTINCT p
            FROM Purchase p
            JOIN p.purchaseProducts pp
            WHERE pp.product.id = :productId
            """)
    Page<Purchase> findPurchasesByProductId(@Param("productId") UUID productId,Pageable pageable);
}

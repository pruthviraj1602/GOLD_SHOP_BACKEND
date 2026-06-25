package com.backend.repositories.purchase;

import com.backend.modals.purchase.PurchaseProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProducts, UUID> {
}

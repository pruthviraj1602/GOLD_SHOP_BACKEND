package com.backend.repositories.supplier;

import com.backend.modals.supplier.SupplierBankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierBankDetailsRepository extends JpaRepository<SupplierBankDetails, UUID> {
}

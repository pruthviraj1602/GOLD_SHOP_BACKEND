package com.backend.repositories.supplier;

import com.backend.modals.supplier.SupplierContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierContactDetailsRepository extends JpaRepository<SupplierContactDetails, UUID> {
}

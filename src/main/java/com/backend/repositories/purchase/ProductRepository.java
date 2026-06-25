package com.backend.repositories.purchase;

import com.backend.modals.purchase.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product , UUID> {
    Optional<Product> findByNameAndCategory(String name, String category);
}

package com.backend.repositories.purchase;

import com.backend.modals.purchase.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product , UUID> {
    Optional<Product> findByNameAndCategory(String name, String category);


    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
   List<Product> findByNameContainingIgnoreCase(String keyword);
}

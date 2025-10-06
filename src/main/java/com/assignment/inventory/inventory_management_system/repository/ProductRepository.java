package com.assignment.inventory.inventory_management_system.repository;

import com.assignment.inventory.inventory_management_system.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findBySku(String sku);
    boolean existsBySku(String sku);
    void deleteBySku(String sku);

    @Query("SELECT p FROM Product p WHERE p.stockQuantity < p.lowStockThreshold")
    List<Product> findAllBelowStockThreshold();
}

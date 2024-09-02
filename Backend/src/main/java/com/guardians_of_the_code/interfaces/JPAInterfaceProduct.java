package com.guardians_of_the_code.interfaces;

import com.guardians_of_the_code.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JPAInterfaceProduct extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);

    Optional<Product> findById(UUID uuid);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:search%")
    List<Product> findProductsByName(@Param("search") String search);

    @Query("SELECT p FROM Product p WHERE p.category IN :categories")
    List<Product> findProductsByCategories(@Param("categories") List<String> categories);

    void deleteById(UUID uuid);

    boolean existsById(UUID uuid);
}

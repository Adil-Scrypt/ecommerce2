package com.adilzhanabdambayev.ecommerce2.repository;

import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdilzhanAbdambayevCategoryRepository extends JpaRepository<AdilzhanAbdambayevCategory, Long> {

    Optional<AdilzhanAbdambayevCategory> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
}

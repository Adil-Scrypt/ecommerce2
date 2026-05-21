package com.adilzhanabdambayev.ecommerce2.repository;

import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdilzhanAbdambayevProductRepository extends JpaRepository<AdilzhanAbdambayevProduct, Long> {

    @Query("""
            select p from AdilzhanAbdambayevProduct p
            where (:search is null or lower(p.name) like lower(concat('%', :search, '%')))
              and (:category is null or lower(p.category) = lower(:category))
            """)
    Page<AdilzhanAbdambayevProduct> findProducts(
            @Param("search") String search,
            @Param("category") String category,
            Pageable pageable
    );
}

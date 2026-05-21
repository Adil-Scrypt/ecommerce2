package com.adilzhanabdambayev.ecommerce2.repository;

import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdilzhanAbdambayevUserRepository extends JpaRepository<AdilzhanAbdambayevUser, Long> {

    Optional<AdilzhanAbdambayevUser> findByEmail(String email);

    boolean existsByEmail(String email);
}

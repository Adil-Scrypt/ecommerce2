package com.adilzhanabdambayev.ecommerce2.repository;

import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdilzhanAbdambayevRoleRepository extends JpaRepository<AdilzhanAbdambayevRole, Long> {

    Optional<AdilzhanAbdambayevRole> findByName(String name);
}

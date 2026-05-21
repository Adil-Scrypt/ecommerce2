package com.adilzhanabdambayev.ecommerce2.repository;

import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevOrder;
import com.adilzhanabdambayev.ecommerce2.entity.AdilzhanAbdambayevUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdilzhanAbdambayevOrderRepository extends JpaRepository<AdilzhanAbdambayevOrder, Long> {

    List<AdilzhanAbdambayevOrder> findByUserOrderByCreatedAtDesc(AdilzhanAbdambayevUser user);
}

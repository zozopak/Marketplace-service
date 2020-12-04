package com.mapsa.marketplace.repository;

import com.mapsa.marketplace.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {
}

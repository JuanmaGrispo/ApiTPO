package com.apiTPO.technologyHouse.app.repositories;

import com.apiTPO.technologyHouse.app.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

package com.apiTPO.technologyHouse.app.repositories;

import com.apiTPO.technologyHouse.app.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

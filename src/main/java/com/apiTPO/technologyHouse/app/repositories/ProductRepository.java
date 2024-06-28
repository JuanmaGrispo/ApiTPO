package com.apiTPO.technologyHouse.app.repositories;

import com.apiTPO.technologyHouse.app.models.Category;
import com.apiTPO.technologyHouse.app.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}

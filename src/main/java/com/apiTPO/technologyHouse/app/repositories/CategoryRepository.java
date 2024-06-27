package com.apiTPO.technologyHouse.app.repositories;

import com.apiTPO.technologyHouse.app.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

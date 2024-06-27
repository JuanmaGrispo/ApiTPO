package com.apiTPO.technologyHouse.app.repositories;

import com.apiTPO.technologyHouse.app.models.ShoppingCart;
import com.apiTPO.technologyHouse.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUser(User user);
    void deleteByUserId(Long userId);
}
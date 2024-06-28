package com.apiTPO.technologyHouse.app.repositories;

import com.apiTPO.technologyHouse.app.models.CartProduct;
import com.apiTPO.technologyHouse.app.models.Product;
import com.apiTPO.technologyHouse.app.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    Optional<CartProduct> findByShoppingCartAndProduct(ShoppingCart shoppingCart, Product product);
}

package com.apiTPO.technologyHouse.app.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart_products")
@Data
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int amount;
}

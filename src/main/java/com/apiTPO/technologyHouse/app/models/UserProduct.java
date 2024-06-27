package com.apiTPO.technologyHouse.app.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_products")
@Data
public class UserProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int amount;
}

package com.apiTPO.technologyHouse.app.dtos;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private float price;
    private Long categoryId;
}

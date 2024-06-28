package com.apiTPO.technologyHouse.app.controllers;

import com.apiTPO.technologyHouse.app.dtos.ProductDTO;
import com.apiTPO.technologyHouse.app.models.Product;
import com.apiTPO.technologyHouse.app.models.SortStrategy;
import com.apiTPO.technologyHouse.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/sortedBy/{strategy}")
    public ResponseEntity<List<Product>> getAll(@PathVariable SortStrategy strategy) {
        List<Product> products = productService.getAll(strategy);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO) {
        Product productCreated = productService.create(productDTO);
        return ResponseEntity.ok(productCreated);
    }

    @GetMapping("/getByCategory/{categoryId}")
    public ResponseEntity<List<Product>> getByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = productService.getByCategory(categoryId);
        return ResponseEntity.ok(products);
    }

}

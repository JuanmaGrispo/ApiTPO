package com.apiTPO.technologyHouse.app.controllers;

import com.apiTPO.technologyHouse.app.dtos.ProductDTO;
import com.apiTPO.technologyHouse.app.models.Product;
import com.apiTPO.technologyHouse.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.getAll();
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
}

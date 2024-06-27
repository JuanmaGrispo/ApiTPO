package com.apiTPO.technologyHouse.app.services;

import com.apiTPO.technologyHouse.app.dtos.ProductDTO;
import com.apiTPO.technologyHouse.app.models.Category;
import com.apiTPO.technologyHouse.app.models.Product;
import com.apiTPO.technologyHouse.app.repositories.CategoryRepository;
import com.apiTPO.technologyHouse.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Product create(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        return productRepository.save(product);
    }
}


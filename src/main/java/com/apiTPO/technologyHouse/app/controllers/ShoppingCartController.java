package com.apiTPO.technologyHouse.app.controllers;

import com.apiTPO.technologyHouse.app.dtos.CartProductDTO;
import com.apiTPO.technologyHouse.app.dtos.CartProductIdDTO;
import com.apiTPO.technologyHouse.app.dtos.UserIdDTO;
import com.apiTPO.technologyHouse.app.models.CartProduct;
import com.apiTPO.technologyHouse.app.models.ShoppingCart;
import com.apiTPO.technologyHouse.app.models.User;
import com.apiTPO.technologyHouse.app.repositories.CartProductRepository;
import com.apiTPO.technologyHouse.app.repositories.ShoppingCartRepository;
import com.apiTPO.technologyHouse.app.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private CartProductRepository cartProductRepository;

    @GetMapping
    public ResponseEntity<ShoppingCart> getByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ShoppingCart cart = shoppingCartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("ShoppingCart not found"));
        return ResponseEntity.ok(cart);
    }


    @PostMapping("/addProduct")
    public ResponseEntity<CartProduct> create(@RequestBody CartProductDTO cartProductDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ShoppingCart cart = shoppingCartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("ShoppingCart not found"));
        CartProduct createdCartProduct = shoppingCartService.addItem(cartProductDTO, cart);
        return ResponseEntity.ok(createdCartProduct);
    }
    @DeleteMapping("/removeProduct")
    public ResponseEntity<Boolean> deleteProduct(@RequestBody CartProductIdDTO cartProductIdDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ShoppingCart cart = shoppingCartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("ShoppingCart not found"));
        return ResponseEntity.ok(shoppingCartService.deleteProduct(cartProductIdDTO, cart));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ShoppingCart>> getAll() {
        List<ShoppingCart> cart = shoppingCartService.getAll();
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/emptyCart")
    public ResponseEntity<Boolean> emptyCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ShoppingCart cart = shoppingCartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("ShoppingCart not found"));
        Boolean result = shoppingCartService.emptyCart(cart);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/confirm")
    public ResponseEntity<Boolean> confirm() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ShoppingCart cart = shoppingCartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("ShoppingCart not found"));
        Boolean result = shoppingCartService.emptyCart(cart);
        return ResponseEntity.ok(result);
    }
}

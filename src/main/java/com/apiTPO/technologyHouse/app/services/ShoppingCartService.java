package com.apiTPO.technologyHouse.app.services;

import com.apiTPO.technologyHouse.app.dtos.CartProductDTO;
import com.apiTPO.technologyHouse.app.dtos.CartProductIdDTO;
import com.apiTPO.technologyHouse.app.dtos.UserIdDTO;
import com.apiTPO.technologyHouse.app.models.CartProduct;
import com.apiTPO.technologyHouse.app.models.Product;
import com.apiTPO.technologyHouse.app.models.ShoppingCart;
import com.apiTPO.technologyHouse.app.repositories.CartProductRepository;
import com.apiTPO.technologyHouse.app.repositories.ProductRepository;
import com.apiTPO.technologyHouse.app.repositories.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, CartProductRepository cartProductRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartProductRepository = cartProductRepository;
        this.productRepository = productRepository;
    }

    public List<ShoppingCart> getAll() {
        return shoppingCartRepository.findAll();
    }

    public CartProduct addItem(CartProductDTO cartProductDTO, ShoppingCart cart) {
        Product product = productRepository.findById(cartProductDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartProduct cartProduct = cartProductRepository.findByShoppingCartAndProduct(cart, product)
                .orElse(null);

        if (cartProduct != null) {
            cartProduct.setAmount(cartProduct.getAmount() + cartProductDTO.getAmount());
        } else {

            cartProduct = new CartProduct();
            cartProduct.setProduct(product);
            cartProduct.setShoppingCart(cart);
            cartProduct.setAmount(cartProductDTO.getAmount());
        }

        return cartProductRepository.save(cartProduct);
    }

    @Transactional
    public Boolean deleteProduct(CartProductIdDTO cartProductIdDTO, ShoppingCart cart) {
        CartProduct cartProduct = cartProductRepository.findById(cartProductIdDTO.getCartProductId())
                .orElse(null);

        if (cartProduct != null && cartProduct.getShoppingCart().getId().equals(cart.getId())) {
            cartProductRepository.deleteById(cartProductIdDTO.getCartProductId());
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean emptyCart(ShoppingCart shoppingCart) {
        List<CartProduct> cartProducts = cartProductRepository.findByShoppingCart(shoppingCart);

        if (cartProducts != null && !cartProducts.isEmpty()) {
            cartProductRepository.deleteAllByShoppingCart(shoppingCart);
            return true;
        }
        return false;
    }
}


package com.Vibecommerce.Ecom_cart.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Vibecommerce.Ecom_cart.Entity.CartItem;
import com.Vibecommerce.Ecom_cart.Entity.Product;
import com.Vibecommerce.Ecom_cart.Repository.CartRepository;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

    public double getCartTotal() {
        return getCartItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    public CartItem addToCart(int productId, int quantity) {
        
        Product product = productService.getProductById(productId);
        if (product == null) {
            throw new RuntimeException("Product not found with ID: " + productId);
        }

        CartItem existingItem = cartRepository.findByProductId(productId).orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            return cartRepository.save(existingItem);
        }

        CartItem newItem = new CartItem();
        newItem.setProductId(product.getId());
        newItem.setProductName(product.getName());
        newItem.setPrice(product.getPrice());
        newItem.setQuantity(quantity);

        return cartRepository.save(newItem);
    }

    public void removeFromCart(int id) {
        cartRepository.deleteById(id);
    }

    public void clearCart() {
        cartRepository.deleteAll();
    }
}

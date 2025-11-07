package com.Vibecommerce.Ecom_cart.Controller;

import com.Vibecommerce.Ecom_cart.Entity.CartItem;
import com.Vibecommerce.Ecom_cart.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public Map<String, Object> getCart() {
        List<CartItem> items = cartService.getCartItems();
        double total = cartService.getCartTotal();
        Map<String, Object> response = new HashMap<>();
        response.put("items", items);
        response.put("total", total);
        return response;
    }

    @PostMapping
    public CartItem addToCart(@RequestBody Map<String, Object> payload) {
        int productId = (int) payload.get("productId");
        int qty = (int) payload.get("qty");
        return cartService.addToCart(productId, qty);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> removeFromCart(@PathVariable int id) {
        cartService.removeFromCart(id);
        return Map.of("message", "Item removed successfully");
    }

    @PostMapping("/checkout")
    public Map<String, Object> checkout() {
        double total = cartService.getCartTotal();
        Map<String, Object> receipt = new HashMap<>();
        receipt.put("total", total);
        receipt.put("timestamp", new Date());
        receipt.put("message", "Checkout successful");
        cartService.clearCart();
        return receipt;
    }
}

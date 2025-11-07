package com.Vibecommerce.Ecom_cart.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Vibecommerce.Ecom_cart.Entity.CartItem;
import com.Vibecommerce.Ecom_cart.Entity.Receipt;
import com.Vibecommerce.Ecom_cart.Service.CartService;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin(origins = "*")
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public Receipt checkout(@RequestBody List<CartItem> cartItems) {
        double total = cartService.getCartTotal();
        Receipt receipt = new Receipt(cartItems, total);
        cartService.clearCart();
        return receipt;
    }
}

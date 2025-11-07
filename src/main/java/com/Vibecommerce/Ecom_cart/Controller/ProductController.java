package com.Vibecommerce.Ecom_cart.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Vibecommerce.Ecom_cart.Entity.Product;
import com.Vibecommerce.Ecom_cart.Service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
    	return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
    	return productService.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) { 
    	return productService.addProduct(product); 
    }
}

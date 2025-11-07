package com.Vibecommerce.Ecom_cart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vibecommerce.Ecom_cart.Entity.Product;
import com.Vibecommerce.Ecom_cart.Repository.ProductRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void initMockData() {
        if (productRepository.count() == 0) {
            productRepository.saveAll(List.of(
                new Product("Wireless Headphones", 59.99),
                new Product("Smart Watch", 99.99),
                new Product("Bluetooth Speaker", 39.99),
                new Product("USB-C Cable", 9.99),
                new Product("Portable Charger", 29.99)
            ));
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}

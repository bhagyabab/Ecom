package com.Vibecommerce.Ecom_cart.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    
    public CartItem() {}

    public CartItem(int productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    
    public int getId() { 
    	return id; 
    }
    public void setId(int id) { 
    	this.id = id; 
    }

    public int getProductId() { 
    	return productId; 
    }
    public void setProductId(int productId) {
    	this.productId = productId; 
    }

    public String getProductName() { 
    	return productName; 
    }
    public void setProductName(String productName) { this.productName = productName; }

    public double getPrice() {
    	return price; 
    }
    public void setPrice(double price) {
    	this.price = price;
    }

    public int getQuantity() { 
    	return quantity; 
    }
    public void setQuantity(int quantity) { 
    	this.quantity = quantity; 
    }
}

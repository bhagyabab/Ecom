package com.Vibecommerce.Ecom_cart.Entity;

import java.time.LocalDateTime;
import java.util.List;

public class Receipt {

    private List<CartItem> items;
    private double total;
    private LocalDateTime timestamp;

    public Receipt(List<CartItem> items, double total) {
        this.items = items;
        this.total = total;
        this.timestamp = LocalDateTime.now();
    }

	public List<CartItem> getItems() {
		return items;
	}

	public double getTotal() {
		return total;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}

    
}

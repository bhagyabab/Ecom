package com.Vibecommerce.Ecom_cart.Repository;

import com.Vibecommerce.Ecom_cart.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findByProductId(int productId);
}

package com.shop_api_spring.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface cartRepository extends JpaRepository<CartEntity, Integer> {
    List<CartEntity> findByEmail(String email);
}

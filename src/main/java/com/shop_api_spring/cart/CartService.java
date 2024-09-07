package com.shop_api_spring.cart;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop_api_spring.cart.CartDto.CartDto;
import com.shop_api_spring.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
    private final cartRepository cartRepository;

    public CartEntity addedToCart(CartDto body, MultipartFile file) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setName(body.getName());
        cartEntity.setPrice(body.getPrice());
        cartEntity.setColors(body.getColors());
        cartEntity.setDescription(body.getDescription());
        cartEntity.setEmail(body.getEmail());
        cartEntity.setImage(file.getOriginalFilename());
        return cartRepository.save(cartEntity);
    }

    public List<CartEntity> findByEmail(String email) {
        return cartRepository.findByEmail(email);
    }

    public List<CartEntity> findCarts() {
        return cartRepository.findAll();
    }

    public CartEntity findACart(int id) {
        return cartRepository.findById(id).orElseThrow(() -> new NotFoundException("Cart not found with id: " + id));
    }

    public void removeDataFromCart(int id) {
        cartRepository.deleteById(id);
    }

    public CartEntity editDataCart(int id, CartEntity body) {
        return cartRepository.findById(id).map((c) -> {
            c.setName(body.getName());
            c.setPrice(body.getPrice());
            c.setColors(body.getColors());
            c.setDescription(body.getDescription());
            c.setEmail(body.getEmail());
            c.setImage(body.getImage());
            return cartRepository.save(c);
        }).orElseThrow(() -> new NotFoundException("Product not found with id:" + id));
    }
}

package com.shop_api_spring.cart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shop_api_spring.cart.CartDto.CartDto;
import com.shop_api_spring.exception.NotFoundException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        var errors = new HashMap<String, String>();
        errors.put("error", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addedToCart")
    public ResponseEntity<CartEntity> addedToCart(@Valid @RequestPart("cart") CartDto cart,
            @RequestPart("image") MultipartFile file) {

        CartEntity added = cartService.addedToCart(cart, file);
        return new ResponseEntity<>(added, HttpStatus.CREATED);

    }

    @GetMapping("/findCartByEmail/{email}")
    public List<CartEntity> findAUser(@PathVariable String email) {
        return cartService.findByEmail(email);
    }

    @GetMapping("/findCarts")
    public List<CartEntity> findAUser() {
        return cartService.findCarts();
    }

    @GetMapping("/findACart/{id}")
    public CartEntity findACart(@PathVariable int id) {
        return cartService.findACart(id);
    }

    @DeleteMapping("removeDataFromCart/{id}")
    public void removeDataFromCart(@PathVariable int id) {
        cartService.removeDataFromCart(id);
    }

    @PutMapping("editDataCart/{id}")
    public CartEntity editDataCart(@PathVariable int id, @RequestPart("cart") CartEntity body,
            @RequestPart("image") MultipartFile file) {
        String filename = file.getOriginalFilename();
        body.setImage(filename);
        return cartService.editDataCart(id, body);
    }

}

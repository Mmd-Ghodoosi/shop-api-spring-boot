package com.shop_api_spring.products;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shop_api_spring.exception.NotFoundException;
import com.shop_api_spring.products.dto.productDto;

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
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class productsController {
    private final productsService productsService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundResponseEntity(NotFoundException ex) {
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

    @PostMapping("/createProduct")
    public ResponseEntity<productsEntity> createProduct(@Valid @RequestPart("product") productDto product,
            @RequestPart("image") MultipartFile file) {

        productsEntity createdProduct = productsService.createProduct(product, file);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/findProducts")
    public List<productsEntity> findProducts() {
        return productsService.findProducts();
    }

    @GetMapping("/findAProduct/{id}")
    public productsEntity findAProduct(@PathVariable int id) {
        return productsService.findAProduct(id);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable int id) {
        productsService.deleteProduct(id);
    }

    @PutMapping("editProduct/{id}")
    public productsEntity editProduct(@PathVariable int id, @RequestPart("product") productsEntity body,
            @RequestPart("image") MultipartFile file) {
        String filename = file.getOriginalFilename();
        body.setImage(filename);
        return productsService.editProduct(id, body);
    }

}

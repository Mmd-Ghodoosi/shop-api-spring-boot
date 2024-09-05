package com.shop_api_spring.products;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop_api_spring.exception.NotFoundException;
import com.shop_api_spring.products.dto.productDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class productsService {
    private final productsRepository productsRepository;

    public productsEntity createProduct(productDto body, MultipartFile file) {
        productsEntity product = new productsEntity();
        product.setName(body.getName());
        product.setPrice(body.getPrice());
        product.setColors(body.getColors());
        product.setDescription(body.getDescription());
        product.setImage(file.getOriginalFilename());
        return productsRepository.save(product);
    }

    public List<productsEntity> findProducts() {
        return productsRepository.findAll();
    }

    public productsEntity findAProduct(int id) {
        return productsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id:" + id));
    }

    public void deleteProduct(int id) {
        productsRepository.deleteById(id);
    }

    public productsEntity editProduct(int id, productsEntity body) {
        return productsRepository.findById(id).map((product) -> {
            product.setName(body.getName());
            product.setPrice(body.getPrice());
            product.setColors(body.getColors());
            product.setDescription(body.getDescription());
            product.setImage(body.getImage());
            return productsRepository.save(product);
        }).orElseThrow(() -> new NotFoundException("Product not found with id:" + id));
    }
}

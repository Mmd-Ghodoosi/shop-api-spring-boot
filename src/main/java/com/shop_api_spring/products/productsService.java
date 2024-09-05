package com.shop_api_spring.products;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop_api_spring.exception.NotFoundException;
import com.shop_api_spring.products.dto.ProductDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final productsRepository productRepository;

    public ProductsEntity createProduct(ProductDto body, MultipartFile file) {
        ProductsEntity product = new ProductsEntity();
        product.setName(body.getName());
        product.setPrice(body.getPrice());
        product.setColors(body.getColors());
        product.setDescription(body.getDescription());
        product.setImage(file.getOriginalFilename());
        return productRepository.save(product);
    }

    public List<ProductsEntity> findProducts() {
        return productRepository.findAll();
    }

    public ProductsEntity findAProduct(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id:" + id));
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public ProductsEntity editProduct(int id, ProductsEntity body) {
        return productRepository.findById(id).map((product) -> {
            product.setName(body.getName());
            product.setPrice(body.getPrice());
            product.setColors(body.getColors());
            product.setDescription(body.getDescription());
            product.setImage(body.getImage());
            return productRepository.save(product);
        }).orElseThrow(() -> new NotFoundException("Product not found with id:" + id));
    }
}

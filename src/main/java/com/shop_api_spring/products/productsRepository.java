package com.shop_api_spring.products;

import org.springframework.data.jpa.repository.JpaRepository;

public interface productsRepository extends JpaRepository<ProductsEntity, Integer> {

}

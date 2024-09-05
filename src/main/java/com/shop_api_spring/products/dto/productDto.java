package com.shop_api_spring.products.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProductDto {
        @NotEmpty(message = "name can not be empty")
        private String name;
        @NotEmpty(message = "price can not be empty")
        private String price;
        @NotEmpty(message = "colors can not be empty")
        private String colors;
        @NotEmpty(message = "description can not be empty")
        private String description;

}

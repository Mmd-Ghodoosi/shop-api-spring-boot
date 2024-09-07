package com.shop_api_spring.cart.CartDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CartDto {
    @NotEmpty(message = "name can not be empty")
    private String name;
    @NotEmpty(message = "price can not be empty")
    private String price;
    @NotEmpty(message = "colors can not be empty")
    private String colors;
    @NotEmpty(message = "description can not be empty")
    private String description;
    @NotEmpty(message = "email can not be empty")
    @Email
    private String email;

}
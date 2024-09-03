package com.shop_api_spring.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record usersDto(
        @NotEmpty(message = "email can not be empty") @Email(message = "Email should be valid") String email,
        @NotEmpty(message = "password can not be empty") String password) {

}

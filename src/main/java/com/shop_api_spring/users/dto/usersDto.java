package com.shop_api_spring.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsersDto {
        @NotEmpty(message = "email can not be empty")
        @Email(message = "Email should be valid")
        private String email;
        @NotEmpty(message = "password can not be empty")
        private String password;
}

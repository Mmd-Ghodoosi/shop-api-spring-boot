package com.shop_api_spring.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class usersEntity {

    @Id
    @GeneratedValue
    private int id;

    private String email;

    private String password;
}

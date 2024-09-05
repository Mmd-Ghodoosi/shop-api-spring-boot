package com.shop_api_spring.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface usersRepository extends JpaRepository<UsersEntity, Integer> {
    List<UsersEntity> findByEmail(String email);

}

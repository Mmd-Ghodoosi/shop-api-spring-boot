package com.shop_api_spring.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface usersRepository extends JpaRepository<UsersEntity, Integer> {
    UsersEntity findByEmail(String email);

    List<UsersEntity> findAllByEmail(String email);
}

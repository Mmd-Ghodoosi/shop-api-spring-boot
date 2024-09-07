package com.shop_api_spring.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface usersRepository extends JpaRepository<UsersEntity, Integer> {

}

package com.shop_api_spring.users;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop_api_spring.exception.NotFoundException;
import com.shop_api_spring.users.dto.UsersDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final usersRepository userRepository;

    public UsersEntity createUser(UsersDto body) {
        UsersEntity users = new UsersEntity();
        users.setEmail(body.getEmail());
        users.setPassword(body.getPassword());
        return userRepository.save(users);
    }

    public List<UsersEntity> findUsers() {
        return userRepository.findAll();
    }

    public UsersEntity findAUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));

    }

    public void deleteAUser(int id) {
        userRepository.deleteById(id);
    }

    public UsersEntity updateUser(int id, UsersEntity body) {

        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setEmail(body.getEmail());
                    existingUser.setPassword(body.getPassword());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }
};

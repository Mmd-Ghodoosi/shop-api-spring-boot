package com.shop_api_spring.users;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shop_api_spring.exception.NotFoundException;
import com.shop_api_spring.users.dto.usersDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class usersService {
    private final usersRepository userRepository;

    public usersEntity createUser(usersDto body) {
        usersEntity users = new usersEntity();
        users.setEmail(body.getEmail());
        users.setPassword(body.getPassword());
        return userRepository.save(users);
    }

    public List<usersEntity> findUsers() {
        return userRepository.findAll();
    }

    public usersEntity findAUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with id: " + id));

    }

    public List<usersEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteAUser(int id) {
        userRepository.deleteById(id);
    }

    public usersEntity updateUser(int id, usersEntity body) {

        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setEmail(body.getEmail());
                    existingUser.setPassword(body.getPassword());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }
};

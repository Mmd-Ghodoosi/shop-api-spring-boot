package com.shop_api_spring.users;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop_api_spring.exception.BadRequestException;
import com.shop_api_spring.exception.NotFoundException;
import com.shop_api_spring.users.dto.UsersDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final usersRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsersEntity signup(UsersDto body) {
        List<UsersEntity> usersWithEmail = userRepository.findAllByEmail(body.getEmail());

        if (!usersWithEmail.isEmpty()) {
            throw new BadRequestException("Email is already in use");
        }

        UsersEntity user = new UsersEntity();
        user.setEmail(body.getEmail());
        user.setPassword(passwordEncoder.encode(body.getPassword()));

        return userRepository.save(user);

    }

    public boolean signin(UsersDto body) {
        UsersEntity user = userRepository.findByEmail(body.getEmail());
        return passwordEncoder.matches(body.getPassword(), user.getPassword());

    }

    public List<UsersEntity> findUsers() {
        return userRepository.findAll();
    }

    public UsersEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UsersEntity> findList(String email) {
        return userRepository.findAllByEmail(email);
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

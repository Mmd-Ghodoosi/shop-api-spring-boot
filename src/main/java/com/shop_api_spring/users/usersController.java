package com.shop_api_spring.users;

import org.springframework.web.bind.annotation.RestController;

import com.shop_api_spring.exeption.NotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class usersController {
    private final usersService userService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(NotFoundException ex) {
        var errors = new HashMap<String, String>();
        errors.put("error", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findUsers")
    public List<usersEntity> findUsers() {
        return userService.findUsers();
    }

    @GetMapping("/findAUser/{id}")
    public usersEntity findAUser(@PathVariable int id) {
        return userService.findAUser(id);
    }

    @GetMapping("/findUserByEmail/{email}")
    public List<usersEntity> findAUser(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PostMapping("/createUser")
    public usersEntity createUser(@RequestBody usersEntity body) {

        return userService.createUser(body);
    }

    @DeleteMapping("/deleteAUser/{id}")
    public void deleteAUser(@PathVariable int id) {
        userService.deleteAUser(id);
    }

    @PutMapping("/updateUser/{id}")
    public usersEntity updateUser(@PathVariable int id, @RequestBody usersEntity body) {
        return userService.updateUser(id, body);

    }
}

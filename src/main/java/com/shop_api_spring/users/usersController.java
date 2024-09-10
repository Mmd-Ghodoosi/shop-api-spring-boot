package com.shop_api_spring.users;

import org.springframework.web.bind.annotation.RestController;

import com.shop_api_spring.exception.BadRequestException;
import com.shop_api_spring.exception.NotFoundException;
import com.shop_api_spring.users.dto.UsersDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private final UsersService userService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(NotFoundException ex) {
        var errors = new HashMap<String, String>();
        errors.put("error", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleUserBadRequestException(BadRequestException ex) {
        var errors = new HashMap<String, String>();
        errors.put("error", ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);

        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signup")
    public UsersEntity signup(@Valid @RequestBody UsersDto body) {

        return userService.signup(body);
    }

    @PostMapping("/signin")
    public String signin(@Valid @RequestBody UsersDto body) {

        boolean isAuthenticated = userService.signin(body);
        if (isAuthenticated) {
            return "Login successful";
        } else {
            return "Invalid credentials";

        }

    }

    @GetMapping("/findUsers")
    public List<UsersEntity> findUsers() {
        return userService.findUsers();
    }

    @GetMapping("/findAUser/{id}")
    public UsersEntity findAUser(@PathVariable int id) {
        return userService.findAUser(id);
    }

    @DeleteMapping("/deleteAUser/{id}")
    public void deleteAUser(@PathVariable int id) {
        userService.deleteAUser(id);
    }

    @PutMapping("/updateUser/{id}")
    public UsersEntity updateUser(@PathVariable int id, @RequestBody UsersEntity body) {
        return userService.updateUser(id, body);

    }
}

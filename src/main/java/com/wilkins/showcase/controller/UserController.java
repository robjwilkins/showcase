package com.wilkins.showcase.controller;

import com.wilkins.showcase.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<JsonUser> createUser(@RequestBody String name) {
        return Optional.of(userService.create(name))
                .map(JsonUser::from)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }
}

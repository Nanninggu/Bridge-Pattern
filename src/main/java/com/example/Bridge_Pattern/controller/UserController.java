package com.example.Bridge_Pattern.controller;

import com.example.Bridge_Pattern.dto.User;
import com.example.Bridge_Pattern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public List<User> getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

}

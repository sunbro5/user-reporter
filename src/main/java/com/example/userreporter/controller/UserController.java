package com.example.userreporter.controller;

import com.example.userreporter.model.User;
import com.example.userreporter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public User getUsers(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }
}

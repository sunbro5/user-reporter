package com.example.airbankuser.controller;

import com.example.airbankuser.model.User;
import com.example.airbankuser.service.UserService;
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

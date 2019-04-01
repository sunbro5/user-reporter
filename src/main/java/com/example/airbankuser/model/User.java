package com.example.airbankuser.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String name;
    private String username;
    private String email;
    private List<Post> posts;
}

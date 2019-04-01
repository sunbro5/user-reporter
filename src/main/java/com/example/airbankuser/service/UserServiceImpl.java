package com.example.airbankuser.service;

import com.example.airbankuser.model.Post;
import com.example.airbankuser.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String GET_USER_URL = "https://jsonplaceholder.typicode.com/users/";
    private static final String GET_USER_POSTS_URL = "https://jsonplaceholder.typicode.com/posts";

    private RestTemplate restTemplate;

    @Autowired
    public void restTemplate(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public User getUser(String userId) {
        ResponseEntity<User> response = restTemplate.getForEntity(GET_USER_URL + userId, User.class);
        User user = response.getBody();
        if (user != null) {
            user.setPosts(getPosts(userId));
        }

        return user;
    }

    private List<Post> getPosts(String userId) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(GET_USER_POSTS_URL)
                .query("userId={userId}")
                .buildAndExpand(userId);
        ResponseEntity<Post[]> response = restTemplate.getForEntity(uriComponents.toUriString(), Post[].class);
        return response.getBody() != null ? Arrays.asList(response.getBody()) : Collections.emptyList();
    }

}

package com.example.airbankuser.service;

import com.example.airbankuser.model.Post;
import com.example.airbankuser.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest({UserService.class})
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getUserReturnUser() throws JsonProcessingException {
        User user = new User();
        user.setName("test");
        mock(user, "https://jsonplaceholder.typicode.com/users/5");

        Post post = new Post();
        post.setTitle("tada");
        Post[] posts = Collections.singletonList(post).toArray(new Post[1]);
        mock(posts, "https://jsonplaceholder.typicode.com/posts?userId=5");

        User returnedUser = userService.getUser("5");
        assertEquals(user.getName(), returnedUser.getName());
        assertEquals(post.getTitle(), returnedUser.getPosts().get(0).getTitle());

    }

    @Test
    public void getUserReturnEmptyResponse() throws JsonProcessingException {
        mock(null, "https://jsonplaceholder.typicode.com/users/10");

        User user = userService.getUser("10");
        assertNull(user);
    }

    private void mock(Object object, String requestUrl) throws JsonProcessingException {
        String detailsString =
                objectMapper.writeValueAsString(object);

        this.server.expect(requestTo(requestUrl))
                .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
    }


}
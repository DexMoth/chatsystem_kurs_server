package com.edu.chatsystem.service;

import com.edu.chatsystem.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @InjectMocks
    private UserService userService;


    @Test
    void create() {
        userService.create(new UserEntity("a", "b", "c"));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
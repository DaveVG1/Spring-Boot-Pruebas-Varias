package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    UserService userService;

    private UserDto userMock = new UserDto("12345678A",null,null);

    @Test
    @Order(1)
    public void contextLoads() {
        assertNotNull(userController);
    }

    @Test
    @Order(2)
    void getUserByIdWithStatusOK() {
        when(userService.getUserById("12345678A")).thenReturn(userMock);
        ResponseEntity<?> response = userController.getUserById("12345678A");
        UserDto user = (UserDto) response.getBody();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(user);
    }

}
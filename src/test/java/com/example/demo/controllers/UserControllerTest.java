package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
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
        Mockito.when(userService.getUserById("12345678A")).thenReturn(userMock);
        ResponseEntity<?> response = userController.getUserById("12345678A");
        UserDto user = (UserDto) response.getBody();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(user);
    }

    @Test
    @Order(3)
    void getUserByIdWithStatusBadRequest() {
        ResponseEntity<?> response = userController.getUserById("12345678");
        String msg = (String) response.getBody();
        assertEquals(msg, "DNI no valido");
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
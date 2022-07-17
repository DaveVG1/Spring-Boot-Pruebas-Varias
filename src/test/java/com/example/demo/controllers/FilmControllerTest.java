package com.example.demo.controllers;

import com.example.demo.dtos.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class FilmControllerTest {

    @InjectMocks
    private FilmController filmController;

    @Test
    @Order(1)
    public void contextLoads() {
        assertNotNull(filmController);
    }

    @Test
    void getFilmsByIdWithData() {
        assertFalse(filmController.getFilmsById("12345678A").isEmpty());
    }

    @Test
    void getFilmsByIdWithoutData() {
        assertTrue(filmController.getFilmsById("12345678X").isEmpty());
    }
}
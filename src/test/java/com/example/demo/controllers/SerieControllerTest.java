package com.example.demo.controllers;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SerieControllerTest {

    @Autowired
    private SerieController serieController;

    @Test
    @Order(1)
    public void contextLoads() {
        assertNotNull(serieController);
    }

    @Test
    void getSeriesByIdWithData() {
        assertFalse(serieController.getSeriesById("12345678A").isEmpty());
    }

    @Test
    void getSeriesByIdWithoutData() {
        assertTrue(serieController.getSeriesById("12345678X").isEmpty());
    }
}
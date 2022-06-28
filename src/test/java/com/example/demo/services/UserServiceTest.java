package com.example.demo.services;

import com.example.demo.dtos.FilmDto;
import com.example.demo.dtos.SerieDto;
import com.example.demo.dtos.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @MockBean
    UserService userService;

    private final UserDto userMockWithData = new UserDto("12345678A",
            List.of(
                new FilmDto("Batman", "2000", "Christopher Nolan")),
            List.of(
                new SerieDto("Soprano", "2000", 6L),
                new SerieDto("The Witcher", "2019", 2L)
    ));

    private final UserDto userMockWithoutData = new UserDto("12345678X", new ArrayList<>(),new ArrayList<>());

    @Test
    @Order(1)
    public void contextLoads() {
        assertNotNull(userService);
    }

    @Test
    void getUserByIdWithData() {
        Mockito.when(userService.getUserById("12345678A")).thenReturn(userMockWithData);
        UserDto user = userService.getUserById("12345678A");
        assertNotNull(user);
        assertFalse(user.getFilms().isEmpty());
        assertFalse(user.getSeries().isEmpty());
    }

    @Test
    void getUserByIdWithoutData() {
        Mockito.when(userService.getUserById("12345678X")).thenReturn(userMockWithoutData);
        UserDto user = userService.getUserById("12345678X");
        assertNotNull(user);
        assertTrue(user.getFilms().isEmpty());
        assertTrue(user.getSeries().isEmpty());
    }

}
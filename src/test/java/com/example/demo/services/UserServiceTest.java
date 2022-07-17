package com.example.demo.services;

import com.example.demo.dtos.FilmDto;
import com.example.demo.dtos.SerieDto;
import com.example.demo.dtos.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    private RestTemplate restTemplate;

    private static final String serieUrl = "http://localhost:8080/dni/%s/series";
    private static final String filmUrl = "http://localhost:8080/dni/%s/films";

    private final List<FilmDto> filmDtos = List.of(new FilmDto("Batman", "2000", "Christopher Nolan"));
    private final List<SerieDto> serieDtos = List.of(
            new SerieDto("Soprano", "2000", 6L),
            new SerieDto("The Witcher", "2019", 2L));


    @Test
    @Order(1)
    public void contextLoads() {
        assertNotNull(userService);
    }

    @Test
    void getUserSeriesByIdWithRestTemplateTest() {
        when(restTemplate.getForObject(String.format(serieUrl,"12345678A"),List.class)).thenReturn(serieDtos);
        List<SerieDto> series = userService.getUserSeriesByIdWithRestTemplate("12345678A");
        assertNotNull(series);
    }

    @Test
    void getUserFilmsByIdWithRestTemplateTest() {
        when(restTemplate.getForObject(String.format(filmUrl,"12345678A"),List.class)).thenReturn(filmDtos);
        List<FilmDto> films = userService.getUserFilmsByIdWithRestTemplate("12345678A");
        assertNotNull(films);
    }

    @Test
    void getUserByIdWithData() {
        when(restTemplate.getForObject(String.format(filmUrl,"12345678A"),List.class)).thenReturn(filmDtos);
        when(restTemplate.getForObject(String.format(serieUrl,"12345678A"),List.class)).thenReturn(serieDtos);
        UserDto user = userService.getUserById("12345678A");
        assertNotNull(user);
        assertFalse(user.getFilms().isEmpty());
        assertFalse(user.getSeries().isEmpty());
    }

    @Test
    @MockitoSettings(strictness = Strictness.STRICT_STUBS)
    void getUserByIdWithoutData() {
        lenient().when(restTemplate.getForObject(String.format(filmUrl,"12345678A"),List.class)).thenReturn(null);
        lenient().when(restTemplate.getForObject(String.format(serieUrl,"12345678A"),List.class)).thenReturn(null);
        UserDto user = userService.getUserById("12345678X");
        assertNotNull(user);
        assertNull(user.getFilms());
        assertNull(user.getSeries());
    }

}
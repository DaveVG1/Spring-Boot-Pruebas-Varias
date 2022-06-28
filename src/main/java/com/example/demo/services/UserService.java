package com.example.demo.services;

import com.example.demo.dtos.FilmDto;
import com.example.demo.dtos.SerieDto;
import com.example.demo.dtos.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final String serieUrl = "http://localhost:8080/dni/%s/series";
    private final String filmUrl = "http://localhost:8080/dni/%s/films";

    public UserDto getUserById(String id){
        UserDto user = new UserDto(id,null,null);
        RestTemplate restTemplate = new RestTemplate();
        List<SerieDto> series = restTemplate.getForObject(String.format(serieUrl,id), ArrayList.class);
        List<FilmDto> films = restTemplate.getForObject(String.format(filmUrl,id), ArrayList.class);
        user.setSeries(series);
        user.setFilms(films);
        return user;
    }
}

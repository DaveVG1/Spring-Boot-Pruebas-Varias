package com.example.demo.services;

import com.example.demo.dtos.FilmDto;
import com.example.demo.dtos.SerieDto;
import com.example.demo.dtos.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    private static final String serieUrl = "http://localhost:8080/dni/%s/series";
    private static final String filmUrl = "http://localhost:8080/dni/%s/films";
    @Autowired
    private RestTemplate restTemplate;
    public UserDto getUserById(String id){
        System.out.println(Thread.currentThread().getName() +
                " -> getUserById " + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        return new UserDto(id,getUserFilmsByIdWithRestTemplate(id),getUserSeriesByIdWithRestTemplate(id));
    }

    public List<SerieDto> getUserSeriesByIdWithRestTemplate(String id){
        try {
            return Executors.newSingleThreadExecutor().submit(()-> {
                System.out.println(Thread.currentThread().getName() +
                        " -> getUserSeriesByIdWithRestTemplate " +
                        TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
                return restTemplate.getForObject(String.format(serieUrl,id), List.class);
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    public List<FilmDto> getUserFilmsByIdWithRestTemplate(String id){
        try {
            return Executors.newSingleThreadExecutor().submit(()-> {
                System.out.println(Thread.currentThread().getName() +
                        " -> getUserFilmsByIdWithRestTemplate " +
                        TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
                return restTemplate.getForObject(String.format(filmUrl,id), List.class);
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

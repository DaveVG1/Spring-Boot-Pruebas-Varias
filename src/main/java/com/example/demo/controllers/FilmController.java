package com.example.demo.controllers;

import com.example.demo.dtos.FilmDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FilmController {

    private final Map<String, List<FilmDto>> filmsMap = new HashMap<String, List<FilmDto>>();
    public FilmController(){
        super();
        filmsMap.put("12345678A", List.of(
                new FilmDto("Batman", "2000", "Christopher Nolan")
        ));
        filmsMap.put("12345678B", List.of(
                new FilmDto("Spider Man", "2002", "Sam Raimi")
        ));
        filmsMap.put("12345678C", List.of(
                new FilmDto("Iron Man","2008","Jon Favreau"),
                new FilmDto("Batman","2000","Christopher Nolan")
        ));
    }

    @GetMapping("/dni/{id}/films")
    public List<FilmDto> getFilmsById(@PathVariable String id){
        if(filmsMap.containsKey(id)){
            return filmsMap.get(id);
        }else{
            return new ArrayList<>();
        }
    }
}

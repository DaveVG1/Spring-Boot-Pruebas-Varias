package com.example.demo.controllers;

import com.example.demo.dtos.FilmDto;
import com.example.demo.dtos.SerieDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SerieController {
    private final Map<String, List<SerieDto>> seriesMap = new HashMap<String, List<SerieDto>>();
    public SerieController(){
        super();
        seriesMap.put("12345678A", List.of(
                new SerieDto("Soprano", "2000", 6L),
                new SerieDto("The Witcher", "2019", 2L)
        ));
        seriesMap.put("12345678B", List.of(
                new SerieDto("Breaking Bad", "2008", 5L)
        ));
        seriesMap.put("12345678C", List.of(
                new SerieDto("The Witcher", "2019", 2L)
        ));
    }

    @GetMapping("/dni/{id}/series")
    public List<SerieDto> getSeriesById(@PathVariable String id){
        return Optional.ofNullable(seriesMap.get(id))
                .orElse(new ArrayList<>());
    }
}

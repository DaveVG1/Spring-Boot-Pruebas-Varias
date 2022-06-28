package com.example.demo.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class FilmDto extends MediaDto{
    private String director;
    public FilmDto(String title, String year, String director){
        super(title, year);
        this.director = director;
    }
}

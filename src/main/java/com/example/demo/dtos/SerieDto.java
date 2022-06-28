package com.example.demo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SerieDto extends MediaDto{
    private Long numberOfSeasons;
    public SerieDto(String title, String year, Long numberOfSeasons){
        super(title, year);
        this.numberOfSeasons = numberOfSeasons;
    }
}

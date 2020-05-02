package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieInfo {
    private int       id;
    private String    title;
    private LocalDate releaseDate;
    private String    genre;

}

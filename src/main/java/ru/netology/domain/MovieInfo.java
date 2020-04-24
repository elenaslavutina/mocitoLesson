package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieInfo {
    private int id;
    private String title;
    private int releaseDate;
    private String genry;

    public int getId() {
        return id;
    }
}

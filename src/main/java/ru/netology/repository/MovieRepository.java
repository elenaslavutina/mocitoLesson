package ru.netology.repository;

import ru.netology.domain.MovieInfo;

public class MovieRepository {

    private MovieInfo[] items = new MovieInfo[]{};

    public void save(MovieInfo item) {
        int length = items.length + 1;
        MovieInfo[] tmp = new MovieInfo[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    public MovieInfo[] findAll() {
        return items;
    }

    public void removeById(int id) {
        int index = 0;

        for (MovieInfo item : items) {
            if (item.getId() != id) {
                index++;
            }
        }

        if (index < items.length) {
            index = 0;
            int length = items.length - 1; // need to keep the length in case of id is not found
            MovieInfo[] tmp = new MovieInfo[length];


            for (MovieInfo item : items) {
                if (item.getId() != id) {
                    tmp[index] = item;
                    index++;
                }
            }
            items = tmp;
        }
    }

    public MovieInfo findById(int id) {
        for (MovieInfo item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeAll() {
        items = new MovieInfo[]{};
    }
}
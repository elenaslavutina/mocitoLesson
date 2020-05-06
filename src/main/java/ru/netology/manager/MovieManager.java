package ru.netology.manager;


import lombok.NoArgsConstructor;
import ru.netology.domain.MovieInfo;

public class MovieManager {

    private MovieInfo[] movies = new MovieInfo[0];
    private int maxMovies;

    MovieManager(int maxMovies) {
        if (maxMovies > 0)
            this.maxMovies = maxMovies;
        else
            this.maxMovies = 10;
    }

    public void add(MovieInfo movie) {
        int length = movies.length + 1;
        MovieInfo[] tmp = new MovieInfo[length];
        System.arraycopy(movies, 0, tmp, 0, movies.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = movie;
        movies = tmp;
    }

    public MovieInfo[] getLastAdded() {
        int count = Math.min(movies.length, this.maxMovies);

        MovieInfo[] result = null;

        if (count > 0) {
            MovieInfo[] items = getAll();

            result = new MovieInfo[count];
            System.arraycopy(items, 0, result, 0, count);
        }

        return result;
    }

    public MovieInfo[] getAll() {

        MovieInfo[] result = new MovieInfo[movies.length];
        for (int i = 0; i < movies.length; i++) {
            int index = movies.length - i - 1;
            result[i] = movies[index];
        }
        return result;
    }

    public void removeById(int id) {
        int index = 0;

        for (MovieInfo item : movies) {
            if (item.getId() != id) {
                index++;
            }
        }

        if (index < movies.length) {
            index = 0;
            int length = movies.length - 1; // need to keep the length in case of id is not found
            MovieInfo[] tmp = new MovieInfo[length];


            for (MovieInfo item : movies) {
                if (item.getId() != id) {
                    tmp[index] = item;
                    index++;
                }
            }
            movies = tmp;
        }
    }


    public void simpleRemoveById(int id) {

        int index  = 0;
        int length = movies.length-1; // need to keep the length in case of id is not found
        MovieInfo[] tmp = new MovieInfo[length];

        for (MovieInfo item : movies) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }

        movies = tmp;
    }
}
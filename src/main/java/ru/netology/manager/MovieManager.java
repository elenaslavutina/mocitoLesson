package ru.netology.manager;


import ru.netology.domain.MovieInfo;
import ru.netology.repository.CartRepository;

public class MovieManager {

    private MovieInfo[] movies = new MovieInfo[0];
    private int maxMovies = 10;

    MovieManager(int maxMovies) {
        this.maxMovies = maxMovies;
    }

    // this method will add movie to the collection we keep inside the class
    public void add(MovieInfo movie) {
        int length = movies.length + 1;
        MovieInfo[] tmp = new MovieInfo[length];
        System.arraycopy(movies, 0, tmp, 0, movies.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = movie;
        movies = tmp;
    }

    public MovieInfo[] findAll() {
        return movies;
    }

    public MovieInfo[] getLastAdded() {
        int count = Math.min(movies.length, this.maxMovies);

        MovieInfo[] items  = getAll();
        MovieInfo[] result = new MovieInfo[count];
        System.arraycopy(items, 0, result, 0, count);

        return result;
    }

    public MovieInfo[] getAll() {
        MovieInfo[] items  = findAll();
        MovieInfo[] result = new MovieInfo[items.length];
        for (int i = 0; i < result.length; i++) {
            int index = items.length - i - 1;
            result[i] = items[index];
        }
        return result;
    }

    public void removeById(int id) {
        int length = movies.length - 1;
        MovieInfo[] tmp = new MovieInfo[length];
        int index = 0;
        for (MovieInfo item : movies) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        movies = tmp;
    }
}

package ru.netology.manager;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.MovieInfo;
import ru.netology.repository.MovieRepository;

@NoArgsConstructor
@Data
public class MovieManager2 {
    private MovieRepository movieRepository = new MovieRepository();
    private int maxMovies = 10;

    public MovieManager2(MovieRepository repository) {
        this.movieRepository = repository;
    }

    public MovieManager2(MovieRepository repository, int maxMovies) {
        this.movieRepository = repository;
        this.maxMovies = maxMovies;
    }

    public void add(MovieInfo item) { movieRepository.save(item); }

    public MovieInfo[] getAll() {
        MovieInfo[] items  = movieRepository.findAll();
        MovieInfo[] result = new MovieInfo[items.length];
        for (int i = 0; i < result.length; i++) {
            int index = items.length - i - 1;
            result[i] = items[index];
        }
        return result;
    }

    public MovieInfo[] getLastAdded() {
        MovieInfo[] movies  = getAll();
        int count = Math.min(movies.length, this.maxMovies);

        MovieInfo[] result = new MovieInfo[count];
        System.arraycopy(movies, 0, result, 0, count);

        return result;
    }


    public void removeById(int id) {
        movieRepository.removeById(id);
    }

    public void removeAll() { movieRepository.removeAll(); }

    public MovieInfo findById(int id) { return movieRepository.findById(id); }

}
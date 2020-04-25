package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.MovieInfo;
import ru.netology.repository.MovieRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MovieManager2TestNoMock {

    MovieRepository repository = new MovieRepository();

    private MovieInfo[] list_of_movies = new MovieInfo[]
            {
                    new MovieInfo(1, "Lake house", 1998, "melodrama"),
                    new MovieInfo(2, "Oscar", 1976, "comedy"),
                    new MovieInfo(3, "Force majeure", 2010, "comedy"),
                    new MovieInfo(4, "Terminator", 1998, "melodrama"),
                    new MovieInfo(5, "Terminator-2", 1976, "comedy"),
                    new MovieInfo(6, "Some movie", 2010, "comedy"),
                    new MovieInfo(7, "Yesterday", 1998, "melodrama"),
                    new MovieInfo(8, "Moscow never sleep", 1976, "comedy"),
                    new MovieInfo(9, "Netology is awesome", 2010, "comedy"),
                    new MovieInfo(10, "Today is the day", 1998, "melodrama"),
                    new MovieInfo(11, "Hello NY", 1976, "comedy"),
                    new MovieInfo(12, "Forest Gump", 2010, "comedy")
            };

    @Test
    public void shouldListLast10Movies() {

        int maxMovies = 10;

        // use constructor with only repository as param
        MovieManager2 customManager = new MovieManager2(repository);

        // add all the items
        for (int i = 0; i < list_of_movies.length; i++) {
            customManager.add(list_of_movies[i]);
        }

        // add items for expected output
        MovieInfo[] expected = new MovieInfo[maxMovies];
        for (int i = 0; i < maxMovies; i++) {
            expected[i] = list_of_movies[(list_of_movies.length - 1) - i];
        }

        MovieInfo[] actual = customManager.getLastAdded();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldListLastCustomNumberOfMovies() {

        int maxMovies = 5;
        MovieManager2 customManager = new MovieManager2(repository, maxMovies);

        // add all the items
        for (int i = 0; i < list_of_movies.length; i++) {
            customManager.add(list_of_movies[i]);
        }

        // add items for expected output
        MovieInfo[] expected = new MovieInfo[maxMovies];
        for (int i = 0; i < maxMovies; i++) {
            expected[i] = list_of_movies[(list_of_movies.length - 1) - i];
        }

        MovieInfo[] actual = customManager.getLastAdded();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldListAllMoviesIfLessThanDefault() {

        int addedMovies = 7;

        MovieManager2 customManager = new MovieManager2(repository, 10);

        // add only limited number which is less then maxMovies
        for (int i = 0; i < addedMovies; i++) {
            customManager.add(list_of_movies[i]);
        }

        // add items for expected output
        MovieInfo[] expected = new MovieInfo[addedMovies];
        for (int i = (addedMovies - 1); i >=0; i--) {
            expected[(addedMovies - 1)-i] = list_of_movies[i];
        }

        MovieInfo[] actual = customManager.getLastAdded();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveAll() {

        MovieManager2 customManager = new MovieManager2(repository);

        // add all the items
        for (int i = 0; i < list_of_movies.length; i++) {
            customManager.add(list_of_movies[i]);
        }

        // add items for expected output
        MovieInfo[] expected = new MovieInfo[0];

        customManager.removeAll();
        MovieInfo[] actual = customManager.getAll();

        assertArrayEquals(expected, actual);
    }
}
package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.MovieInfo;
import ru.netology.repository.MovieRepository;

import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieManager2NoMockTest {

    private MovieRepository repository = new MovieRepository();

    private MovieInfo[] list_of_movies = new MovieInfo[]
            {
                    new MovieInfo(1, "Lake house", LocalDate.parse("1998-06-01"), "melodrama"),
                    new MovieInfo(2, "Oscar", LocalDate.parse("1976-06-01"), "comedy"),
                    new MovieInfo(3, "Force majeure", LocalDate.parse("2010-06-01"), "comedy"),
                    new MovieInfo(4, "Terminator", LocalDate.parse("1998-06-01"), "melodrama"),
                    new MovieInfo(5, "Terminator-2", LocalDate.parse("1998-06-01"), "comedy"),
                    new MovieInfo(6, "Some movie", LocalDate.parse("2010-06-01"), "comedy"),
                    new MovieInfo(7, "Yesterday", LocalDate.parse("2010-06-01"), "melodrama"),
                    new MovieInfo(8, "Moscow never sleep", LocalDate.parse("1976-06-01"), "comedy"),
                    new MovieInfo(9, "Netology is awesome", LocalDate.parse("1976-06-01"), "comedy"),
                    new MovieInfo(10, "Today is the day", LocalDate.parse("1998-06-01"), "melodrama"),
                    new MovieInfo(11, "Hello NY", LocalDate.parse("1976-06-01"), "comedy"),
                    new MovieInfo(12, "Forest Gump", LocalDate.parse("1998-06-01"), "comedy")
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
        for (int i = (addedMovies - 1); i >= 0; i--) {
            expected[(addedMovies - 1) - i] = list_of_movies[i];
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

    @Test
    public void shouldFindById() {

        MovieManager2 customManager = new MovieManager2(repository);

        // add all the items
        for (int i = 0; i < list_of_movies.length; i++) {
            customManager.add(list_of_movies[i]);
        }

        Random rand = new Random();

        int idx = rand.nextInt(list_of_movies.length);

        // add items for expected output
        MovieInfo expected = list_of_movies[idx];

        // since we numerates IDs in list_of_movies from 1 we need to send idx + 1
        MovieInfo actual = customManager.findById(idx + 1);

        assertEquals(expected, actual);
    }


    @Test
    public void shouldRemoveById() {

        MovieManager2 customManager = new MovieManager2(repository);

        MovieInfo first  = new MovieInfo(1, "Lake house", LocalDate.parse("1998-06-01"), "melodrama");
        MovieInfo second = new MovieInfo(2,  "Oscar", LocalDate.parse("1976-06-01"), "comedy");
        MovieInfo third  = new MovieInfo(3,  "Force majeure", LocalDate.parse("2010-06-01"), "comedy");

        customManager.add(first);
        customManager.add(second);
        customManager.add(third);

        customManager.removeById(2);
        MovieInfo[] expected = new MovieInfo[]{third, first};
        MovieInfo[] actual = customManager.getAll();
        assertArrayEquals(expected, actual);

    }


    @Test
    public void shouldReturnNullIfNotExists() {

        MovieManager2 customManager = new MovieManager2(repository);

        // add all the items
        for (int i = 0; i < list_of_movies.length; i++) {
            customManager.add(list_of_movies[i]);
        }

        Random rand = new Random();

        int idx = list_of_movies.length + rand.nextInt(100);

        // add items for expected output
        MovieInfo expected = null;

        // since we numerates IDs in list_of_movies from 1 we need to send idx + 1
        MovieInfo actual = customManager.findById(idx + 1);

        assertEquals(expected, actual);
    }
}
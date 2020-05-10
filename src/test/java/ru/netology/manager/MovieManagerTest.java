package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.MovieInfo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MovieManagerTest {

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
    public void shouldRemoveIfExists() {
        int idToRemove = 1;

        MovieManager customManager = new MovieManager(10);

        customManager.add(list_of_movies[0]);
        customManager.add(list_of_movies[1]);
        customManager.add(list_of_movies[2]);

        customManager.removeById(idToRemove);
        MovieInfo[] expected = new MovieInfo[]{list_of_movies[2], list_of_movies[1]};
        MovieInfo[] actual = customManager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveIfNotExists() {
        int idToRemove = 1;

        MovieManager customManager = new MovieManager(10);

        customManager.add(list_of_movies[1]);
        customManager.add(list_of_movies[2]);

        customManager.removeById(idToRemove);
        MovieInfo[] expected = new MovieInfo[]{list_of_movies[2], list_of_movies[1]};
        MovieInfo[] actual = customManager.getAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldListLast10Movies() {

        int maxMovies = 10;
        MovieManager customManager = new MovieManager(maxMovies);

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
        MovieManager customManager = new MovieManager(maxMovies);

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
    public void shouldListMoreThanDefault() {

        int maxMovies = 12;
        MovieManager customManager = new MovieManager(maxMovies);

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
    public void  shouldListNofingifNegative() {

        int maxMovies  = -1;
        int defaultVal = 10;
        MovieManager customManager = new MovieManager(maxMovies);

        // add all the items
        for (int i = 0; i < list_of_movies.length; i++) {
            customManager.add(list_of_movies[i]);
        }

        // add items for expected output
        MovieInfo[] expected = new MovieInfo[defaultVal];
        for (int i = 0; i < defaultVal; i++) {
            expected[i] = list_of_movies[(list_of_movies.length - 1) - i];
        }

        MovieInfo[] actual = customManager.getLastAdded();

        assertArrayEquals(expected, actual);

    }

    @Test
    public void  shouldListNofingifZero() {

        int maxMovies  = 0;
        int defaultVal = 10;

        MovieManager customManager = new MovieManager(maxMovies);

        // add all the items
        for (int i = 0; i < list_of_movies.length; i++) {
            customManager.add(list_of_movies[i]);
        }

        MovieInfo[] actual = customManager.getLastAdded();

        // add items for expected output
        MovieInfo[] expected = new MovieInfo[defaultVal];
        for (int i = 0; i < defaultVal; i++) {
            expected[i] = list_of_movies[(list_of_movies.length - 1) - i];
        }

        assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldListAllMoviesIfLessThanDefault() {

        int maxMovies   = 10;
        int addedMovies = 7;
        MovieManager customManager = new MovieManager(maxMovies);

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
    public void shouldListListOne() {

        int maxMovies   = 1;

        MovieManager customManager = new MovieManager(maxMovies);

        // add only limited number which is less then maxMovies
        for (int i = 0; i < list_of_movies.length; i++) {
            customManager.add(list_of_movies[i]);
        }

        MovieInfo[] actual = customManager.getLastAdded();

        MovieInfo expected = list_of_movies[list_of_movies.length-1];

        assertEquals(1, actual.length);
        assertEquals(expected, actual[0]);
    }
}
package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.MovieInfo;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


//@ExtendWith(MockitoExtension.class)
public class MovieManagerTest {

//   @Mock
//   private CartRepository repository;
//   @InjectMocks
    private MovieManager manager = new MovieManager(10);
    private MovieInfo first  = new MovieInfo(1, "Lake house", 1998, "melodrama");
    private MovieInfo second = new MovieInfo(2,  "Oscar", 1976, "comedy");
    private MovieInfo third  = new MovieInfo(3,  "Force majeure", 2010, "comedy");

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


@BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
    }

    @Test
    public void shouldRemoveIfExists() {
        int idToRemove = 1;

        manager.removeById(idToRemove);
        MovieInfo[] expected = new MovieInfo[]{third, second};
        MovieInfo[] actual = manager.getAll();
        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldNotRemoveIfNotExists() {
        int idToRemove = 1;

        manager.removeById(idToRemove);
        MovieInfo[] expected = new MovieInfo[]{third, second};
        MovieInfo[] actual = manager.getAll();

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


}


package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.MovieInfo;
import ru.netology.repository.MovieRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MovieManagerMockTest {

    private MovieInfo first, second, third;

    @Mock
    private MovieRepository repository;
    @InjectMocks
    private MovieManager manager;

    @BeforeEach
    public void addData() {
        first  = new MovieInfo(1, "Lake house", LocalDate.parse("1998-06-01"), "melodrama");
        second = new MovieInfo(2,  "Oscar", LocalDate.parse("1976-06-01"), "comedy");
        third  = new MovieInfo(3,  "Force majeure", LocalDate.parse("2010-06-01"), "comedy");

        manager.add(first);
        manager.add(second);
        manager.add(third);
    }

    @Test
    public void shouldRemoveIfExists() {

        int idToRemove = 1;

        MovieInfo[] returned = new MovieInfo[]{second, third};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).removeById(idToRemove);

        manager.removeById(idToRemove);
        MovieInfo[] expected = new MovieInfo[]{third, second};
        MovieInfo[] actual = manager.getAll();
        assertArrayEquals(expected, actual);

        verify(repository).removeById(idToRemove);
    }

    @Test
    public void shouldNotRemoveIfWrongID() {

        int idToRemove = 33;

        MovieInfo[] returned = new MovieInfo[]{first, second, third};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).removeById(idToRemove);

        manager.removeById(idToRemove);
        MovieInfo[] expected = new MovieInfo[]{third, second, first};
        MovieInfo[] actual = manager.getAll();
        assertArrayEquals(expected, actual);

        verify(repository).removeById(idToRemove);
    }

}
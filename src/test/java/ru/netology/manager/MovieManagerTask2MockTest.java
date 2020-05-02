package ru.netology.manager;

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
public class MovieManagerTask2MockTest {

    @Mock
    private MovieRepository repository;
    @InjectMocks
    private MovieManagerTask2 manager;

    @Test
    public void shouldRemoveIfExists() {
        MovieInfo first  = new MovieInfo(1, "Lake house", LocalDate.parse("1998-06-01"), "melodrama");
        MovieInfo second = new MovieInfo(2,  "Oscar", LocalDate.parse("1976-06-01"), "comedy");
        MovieInfo third  = new MovieInfo(3,  "Force majeure", LocalDate.parse("2010-06-01"), "comedy");

        manager.add(first);
        manager.add(second);
        manager.add(third);

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
}
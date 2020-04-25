package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.MovieInfo;
import ru.netology.repository.MovieRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MovieManager2MockTest {

    @Mock
    private MovieRepository repository;
    @InjectMocks
    private MovieManager2   manager;

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
    public void shouldRemoveIfExists() {
        MovieInfo first  = new MovieInfo(1, "Lake house", 1998, "melodrama");
        MovieInfo second = new MovieInfo(2,  "Oscar", 1976, "comedy");
        MovieInfo third  = new MovieInfo(3,  "Force majeure", 2010, "comedy");

        manager.add(first);
        manager.add(second);
        manager.add(third);

        int idToRemove = 1;
        // настройка заглушки
        MovieInfo[] returned = new MovieInfo[]{second, third};
        doReturn(returned).when(repository).findAll();
        doNothing().when(repository).removeById(idToRemove);

        manager.removeById(idToRemove);
        MovieInfo[] expected = new MovieInfo[]{third, second};
        MovieInfo[] actual = manager.getAll();
        assertArrayEquals(expected, actual);

        // удостоверяемся, что заглушка была вызвана с нужным значением
        // но это уже проверка "внутренней" реализации
        verify(repository).removeById(idToRemove);
    }
}
package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.MovieInfo;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


@ExtendWith(MockitoExtension.class)
public class MovieManagerTest {

 //   @Mock
 //   private CartRepository repository;
 //   @InjectMocks
    private MovieManager manager;
    private MovieInfo first = new MovieInfo(1, "Lake house", 1998, "melodrama");
    private MovieInfo second = new MovieInfo(2,  "Oscar", 1976, "comedy");
    private MovieInfo third = new MovieInfo(3,  "Force majeure", 2010, "comedy");

    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
    }

    @Test
    public void shouldRemoveIfExists() {
        int idToRemove = 1;
        // настройка заглушки
        MovieInfo[] returned = new MovieInfo[]{second, third};
    //    doReturn(returned).when(repository).findAll();
      //  doNothing().when(repository).removeById(idToRemove);

        manager.removeById(idToRemove);
        MovieInfo[] expected = new MovieInfo[]{third, second};
        MovieInfo[] actual = manager.getAll();
        assertArrayEquals(expected, actual);

        // удостоверяемся, что заглушка была вызвана с нужным значением
        // но это уже проверка "внутренней" реализации
  //      verify(repository).removeById(idToRemove);
    }

    @Test
    public void shouldNotRemoveIfNotExists() {
        int idToRemove = 4;
        MovieInfo[] returned = new MovieInfo[]{first, second, third};
     //   doReturn(returned).when(repository).findAll();
       // doNothing().when(repository).removeById(idToRemove);

        manager.removeById(idToRemove);
        MovieInfo[] expected = new MovieInfo[]{third, second, first};
        MovieInfo[] actual = manager.getAll();

        assertArrayEquals(expected, actual);
        // удостоверяемся, что заглушка была вызвана с нужным значением
        // но это уже проверка "внутренней" реализации
   //     verify(repository).removeById(idToRemove);
    }
}


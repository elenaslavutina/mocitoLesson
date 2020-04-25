package ru.netology.repository;

import ru.netology.domain.MovieInfo;

public class MovieRepository {

    private MovieInfo[] items = new MovieInfo[]{};

//  save - добавляет объект в массив
    public void save(MovieInfo item) {
        int length = items.length + 1;
        MovieInfo[] tmp = new MovieInfo[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

//  findAll - возвращает массив всех хранящихся в массиве объектов
    public MovieInfo[] findAll() {
        return items;
    }

//  removeById - удаляет объект по идентификатору (если объекта нет, то пусть будет исключение, как на лекции)
    public void removeById(int id) {
        int length = items.length - 1;
        MovieInfo[] tmp = new MovieInfo[length];
        int index = 0;
        for (MovieInfo item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }

//  findById - возвращает объект по идентификатору (либо null, если такого объекта нет)
    public MovieInfo findById(int id) {
        for (MovieInfo item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

//  removeAll* - полностью вычищает репозиторий
    public void removeAll() {
        items = new MovieInfo[]{};
    }
}
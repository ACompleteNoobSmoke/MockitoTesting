package com.acompletenoobsmoke.mockito.test_doubles.fake.Film;

import java.util.Collection;

public interface FilmRepository {
    void save(Film film);
    Collection<Film> findAll();
}

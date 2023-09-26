package com.acompletenoobsmoke.mockito.test_doubles.dummy.Film;

import java.util.Collection;

public interface FilmRepository {
    void save(Film film);
    Collection<Film> findAll();
}

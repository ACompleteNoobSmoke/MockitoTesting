package com.acompletenoobsmoke.mockito.test_doubles.stub.Film;

import java.util.Collection;
import java.util.List;

public interface FilmRepository {
    void save(Film film);
    Collection<Film> findAll();
    List<Film> findNewBooks(int days);
}

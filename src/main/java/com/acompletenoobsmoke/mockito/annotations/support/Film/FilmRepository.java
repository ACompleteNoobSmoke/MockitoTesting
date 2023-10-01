package com.acompletenoobsmoke.mockito.annotations.support.Film;

import java.util.Collection;
import java.util.List;

public interface FilmRepository {
    void save(Film film);
    Collection<Film> findAll();
    List<Film> findNewBooks(int days);
}

package com.acompletenoobsmoke.mockito.bdd.behavior_verificaion.Film;

import java.util.Collection;

public interface FilmRepository {
    void save(Film film);
    Collection<Film> findAll();
}

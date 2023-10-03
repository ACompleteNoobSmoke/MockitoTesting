package com.acompletenoobsmoke.mockito.bdd.behavior_verificaion.Film;

import java.util.Collection;
import java.util.List;

public interface FilmRepository {
    void save(Film film);
    Collection<Film> findAll();
    List<Film> findNewFilms(int days);
    Film findFilmByID(String bookID);
}

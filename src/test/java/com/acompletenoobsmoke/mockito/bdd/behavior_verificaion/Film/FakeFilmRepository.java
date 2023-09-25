package com.acompletenoobsmoke.mockito.bdd.behavior_verificaion.Film;

import java.util.*;

public class FakeFilmRepository implements FilmRepository{

    Map<String, Film> filmStore = new HashMap<>();

    @Override
    public void save(Film film) {
        String filmID = film.getFilmID();
        filmStore.put(filmID, film);
    }

    @Override
    public Collection<Film> findAll() {
        return filmStore.values();
    }
}

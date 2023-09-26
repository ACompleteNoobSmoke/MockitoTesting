package com.acompletenoobsmoke.mockito.test_doubles.dummy.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FakeFilmRepository implements FilmRepository {

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

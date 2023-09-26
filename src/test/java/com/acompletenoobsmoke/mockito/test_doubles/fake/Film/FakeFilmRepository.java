package com.acompletenoobsmoke.mockito.test_doubles.fake.Film;

import com.acompletenoobsmoke.mockito.test_doubles.fake.Film.Film;
import com.acompletenoobsmoke.mockito.test_doubles.fake.Film.FilmRepository;

import java.util.*;

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

package com.acompletenoobsmoke.mockito.test_doubles.stub.Film;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class FilmRepositoryStub implements FilmRepository {
    @Override
    public void save(Film film) {

    }

    @Override
    public Collection<Film> findAll() {
        return null;
    }

    @Override
    public List<Film> findNewBooks(int days) {
        return List.of(
                new Film("2", "The Batman", 40, LocalDate.of(2022, 3, 21)),
                new Film("1", "Spiderman", 33, LocalDate.of(2021, 12, 24)),
                new Film("3", "The Matrix 4", 70, LocalDate.of(2021, 12, 23)),
                new Film("4", "Black Panther 2", 80, LocalDate.of(2022, 11, 15))
        );
    }
}

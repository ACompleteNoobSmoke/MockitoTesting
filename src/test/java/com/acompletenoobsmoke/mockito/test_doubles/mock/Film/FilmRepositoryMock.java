package com.acompletenoobsmoke.mockito.test_doubles.mock.Film;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmRepositoryMock implements FilmRepository {

    int saveCalled = 0;
    Film lastFilmAdded = null;

    @Override
    public void save(Film film) {
        saveCalled++;
        lastFilmAdded = film;
    }

    public void verify(Film film, int times) {
        assertEquals(times, saveCalled);
        assertEquals(film, lastFilmAdded);
    }

}

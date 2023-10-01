package com.acompletenoobsmoke.mockito.test_doubles.spy.Film;

public class FilmRepositorySpy implements FilmRepository {

    int saveCalled = 0;
    Film lastFilmAdded = null;

    @Override
    public void save(Film film) {
        saveCalled++;
        lastFilmAdded = film;
    }

    public int timesCalled() { return saveCalled; }
    public boolean calledWith(Film film) { return lastFilmAdded.equals(film); }
}

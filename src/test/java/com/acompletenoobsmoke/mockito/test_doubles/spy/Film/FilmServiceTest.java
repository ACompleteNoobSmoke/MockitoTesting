package com.acompletenoobsmoke.mockito.test_doubles.spy.Film;

import com.acompletenoobsmoke.mockito.test_doubles.spy.BookService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmServiceTest {

    @Test
    void itShouldAddFilm() {
        FilmRepositorySpy filmRepository = new FilmRepositorySpy();
        FilmService filmService = new FilmService(filmRepository);
        Film film1 = new Film("1", "Casablanca", 90, LocalDate.now());
        Film film2 = new Film("2", "Citizen Kane", 100, LocalDate.now());
        filmService.addFilm(film1);
        filmService.addFilm(film2);
        assertEquals(2, filmRepository.timesCalled());
        assertTrue(filmRepository.calledWith(film2));
    }

    @Test
    void demoSpyWithMockito() {
        FilmRepository filmRepositorySpy = spy(FilmRepository.class);
        FilmService filmService = new FilmService(filmRepositorySpy);

        Film film1 = new Film("3", "From Russia With Love", 100, LocalDate.now());
        Film film2 = new Film("1", "Tomorrow Never Die", 300, LocalDate.now());

        filmService.addFilm(film1);
        filmService.addFilm(film2);

        verify(filmRepositorySpy, times(1)).save(film1);
        verify(filmRepositorySpy, times(1)).save(film2);


    }
}
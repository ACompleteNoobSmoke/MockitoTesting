package com.acompletenoobsmoke.mockito.test_doubles.mock.Film;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class FilmServiceTest {

    @Test
    void itShouldAddFilm() {
        FilmRepositoryMock filmRepository = new FilmRepositoryMock();
        FilmService filmService = new FilmService(filmRepository);
        Film film1 = new Film("1", "Casablanca", 90, LocalDate.now());
        Film film2 = new Film("2", "Citizen Kane", 100, LocalDate.now());
        filmService.addFilm(film1);
        filmService.addFilm(film2);
        filmRepository.verify(film2, 2);
    }

    @Test
    void itShouldAddFilmMock() {
        FilmRepository filmRepository = mock(FilmRepository.class);
        FilmService underTest = new FilmService(filmRepository);

        Film film1 = new Film("3", "Jumanji", 32, LocalDate.now());
        Film film2 = new Film("1", "Rush Hour", 89, LocalDate.now());
        //Film film3 = new Film("4", "No Country For Old Men", 401, LocalDate.now());
        List<Film> listFilm = List.of(film1, film2);

        listFilm.forEach(underTest::addFilm);
        listFilm.forEach(film -> verify(filmRepository).save(film));

        then(filmRepository).should(times(2)).save(any(Film.class));
    }
}
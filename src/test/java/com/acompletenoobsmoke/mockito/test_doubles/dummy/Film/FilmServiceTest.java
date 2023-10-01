package com.acompletenoobsmoke.mockito.test_doubles.dummy.Film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilmServiceTest {

   private FilmService underTest;
   private FilmRepository filmRepository;
   private FilmEmailService emailService;

    @BeforeEach
    public void setUp() {
        this.filmRepository = new FakeFilmRepository();
        this.emailService = new DummyEmailService();
        this.underTest = new FilmService(filmRepository, emailService);
    }

    @Test
    void itShouldAddFilm() {
        Film newFilm = new Film("1", "Avatar", 20, LocalDate.ofYearDay(2009, 21));
        underTest.addFilm(newFilm);
        assertThat(filmRepository.findAll()).anyMatch(film -> film.getFilmID().equals(newFilm.getFilmID()));
    }

    @Test
    void itShouldFindNumberOfFilms() {
        //Given
        Film film1 = new Film("1", "Avatar", 20, LocalDate.ofYearDay(2009, 21));
        Film film2 = new Film("2", "Avatar: Way Of The Water", 25, LocalDate.ofYearDay(2022, 23));
        //When
        underTest.addFilm(film1);
        underTest.addFilm(film2);
        //Then
        assertEquals(2, underTest.findNumberOfFilms());
    }

    @Test
    void demoDummyWithMockito() {
        FilmRepository filmRepository = mock(FilmRepository.class);
        DummyEmailService emailService = mock(DummyEmailService.class);
        FilmService filmService = new FilmService(filmRepository, emailService);

        Film film1 = new Film("1", "Black Panther", 30, LocalDate.now());
        Film film2 = new Film("2", "Black Panther: Way Of The Water", 30, LocalDate.now());
        Collection<Film> filmCollection = List.of(film1, film2);

        when(filmRepository.findAll()).thenReturn(filmCollection);

        assertEquals(2, filmService.findNumberOfFilms());

    }
}
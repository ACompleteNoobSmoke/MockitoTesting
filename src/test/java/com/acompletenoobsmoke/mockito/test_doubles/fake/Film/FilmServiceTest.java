package com.acompletenoobsmoke.mockito.test_doubles.fake.Film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FilmServiceTest {

    private FilmService underTest;
    private FilmRepository filmRepository;

    @BeforeEach
    public void setUp() {
        this.filmRepository = new FakeFilmRepository();
        this.underTest = new FilmService(filmRepository);
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
}
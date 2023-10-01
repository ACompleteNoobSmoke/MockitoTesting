package com.acompletenoobsmoke.mockito.test_doubles.fake.Film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

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

    @Test
    void testFakeWithMockito() {
        FilmRepository filmRepositoryMock = mock(FilmRepository.class);
        FilmService underTestMock = new FilmService(filmRepositoryMock);
        Film newFilm = new Film("3", "Max Payne", 35, LocalDate.now());
        Film newFilm2 = new Film("1", "Hard Boiled", 89, LocalDate.now());
        Collection<Film> filmList = List.of(newFilm, newFilm2);
        when(filmRepositoryMock.findAll()).thenReturn(filmList);
        assertEquals(2, underTestMock.findNumberOfFilms());
    }

    @Test
    void testSaveWithMockito() {
        FilmRepository filmRepositoryMock = mock(FilmRepository.class);
        FilmService underTestMock = new FilmService(filmRepositoryMock);
        Film newFilm = new Film("5", "Finding Nemo", 90, LocalDate.now());
        ArgumentCaptor<Film> filmArgumentCaptor = ArgumentCaptor.forClass(Film.class);
        underTestMock.addFilm(newFilm);
        verify(filmRepositoryMock).save(filmArgumentCaptor.capture());

        Film capturedFilm = filmArgumentCaptor.getValue();
        assertThat(capturedFilm).isNotNull().usingRecursiveComparison().isEqualTo(newFilm);
    }
}
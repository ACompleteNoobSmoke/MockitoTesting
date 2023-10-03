package com.acompletenoobsmoke.mockito.annotations.support;

import com.acompletenoobsmoke.mockito.annotations.support.Film.Film;
import com.acompletenoobsmoke.mockito.annotations.support.Film.FilmRepository;
import com.acompletenoobsmoke.mockito.annotations.support.Film.FilmRequest;
import com.acompletenoobsmoke.mockito.annotations.support.Film.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnnotationsTest2 {

    @Mock
    private FilmRepository filmRepository;
    @InjectMocks
    private FilmService underTest;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        this.underTest = new FilmService(filmRepository);
//    }

    @Test
    public void testStub(){
        //Given
        int discount = 50;
        int days = 10;
        List<Film> filmList = listOfFilms();
        List<Integer> newPrices = calculations(discount, filmList);

        //When
        when(filmRepository.findNewBooks(days)).thenReturn(filmList);
        List<Film> answer = underTest.getNewBooksWithAppliedDiscount(discount, days);

        //Then
       assertThat(answer).extracting(Film::getPrice).containsAll(newPrices);
    }

    private List<Integer> calculations (int discountRate, List<Film> filmList){
        return filmList.stream()
                .map(film -> film.getPrice() - (discountRate * film.getPrice()) / 100)
                .collect(Collectors.toList());
    }

    @Test
    public void testTotalCalculation() {
        List<Film> filmList = listOfFilms();
        List<String> filmIDs = filmList.stream().map(Film::getFilmID).toList();
        filmList.forEach(film -> when(filmRepository.findFilmByID(film.getFilmID())).thenReturn(film));
        int answer = underTest.calculateTotalCost(filmIDs);
        assertEquals(1900, answer);
    }

    private List<Film> listOfFilms() {
        return List.of(
                new Film("12", "Insidious", 400, LocalDate.now()),
                new Film("1", "The Conjuring", 500, LocalDate.now()),
                new Film("3", "Nightmare On Elm Street", 700, LocalDate.now()),
                new Film("5", "Halloween", 300, LocalDate.now())
        );
    }

    @Test
    public void saveFilm() {
        Film newFilm = new Film(null, "Vampire In Brooklyn", 23, LocalDate.of(1995, 12, 22));
        ArgumentCaptor<Film> filmArgumentCaptor = ArgumentCaptor.forClass(Film.class);
        underTest.addFilm(newFilm);
        verify(filmRepository).save(filmArgumentCaptor.capture());
        Film capturedFilm = filmArgumentCaptor.getValue();
        assertThat(capturedFilm).isNotNull().usingRecursiveComparison().ignoringFields("").isEqualTo(newFilm);
    }

    @Test
    public void testSaveFilmWithRequest(){
        Film newFilm = new Film(null, "Lethal Weapon", 250, LocalDate.now());
        FilmRequest filmRequest = new FilmRequest(
                newFilm.getTitle(),
                newFilm.getPrice(),
                newFilm.getReleaseDate()
        );
        ArgumentCaptor<Film> filmArgumentCaptor = ArgumentCaptor.forClass(Film.class);
        underTest.addFilm(filmRequest);
        verify(filmRepository).save(filmArgumentCaptor.capture());
        Film capturedFilm = filmArgumentCaptor.getValue();
        assertThat(capturedFilm).isNotNull().usingRecursiveComparison().ignoringFields("filmID").isEqualTo(newFilm);

    }
}

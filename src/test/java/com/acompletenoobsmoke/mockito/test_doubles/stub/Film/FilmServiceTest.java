package com.acompletenoobsmoke.mockito.test_doubles.stub.Film;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FilmServiceTest {


    private FilmRepository filmRepository;
    private FilmService underTest;

    @BeforeEach
    public void setUp() {
        filmRepository = new FilmRepositoryStub();
        underTest = new FilmService(filmRepository);
    }

    @Test
    void itShouldGetNewBooksWithAppliedDiscount() {
        int days = 10;
        int discountRate = 40;
        List<Film> filmList = filmRepository.findNewBooks(days);
        //when(filmRepository.findNewBooks(days)).thenReturn(filmList);
        List<Integer> resultDiscountPrices = discount(discountRate, filmList);
        List<Film> resultList = underTest.getNewBooksWithAppliedDiscount(discountRate, days);


        assertThat(resultList).extracting(Film::getPrice).containsAll(resultDiscountPrices);
    }

    private List<Film> listOfFilms() {
        return List.of(
                new Film("2", "The Batman", 40, LocalDate.of(2022, 3, 21)),
                new Film("1", "Spiderman", 33, LocalDate.of(2021, 12, 24)),
                new Film("3", "The Matrix 4", 70, LocalDate.of(2021, 12, 23)),
                new Film("4", "Black Panther 2", 80, LocalDate.of(2022, 11, 15))
        );
    }

    @Test
    public void demoStubWithMockito() {
        int discountRate = 30;
        int days = 10;
        FilmRepository filmRepositoryStub = mock(FilmRepository.class);
        FilmService underTestStub = new FilmService(filmRepositoryStub);
        List<Film> filmList = listOfFilms();
        List<Integer> discountList = discount(discountRate, filmList);
        when(filmRepositoryStub.findNewBooks(days)).thenReturn(filmList);
        List<Film> resultList = underTestStub.getNewBooksWithAppliedDiscount(discountRate, days);
        assertThat(resultList).extracting(Film::getPrice).containsAll(discountList);
        assertEquals(4, resultList.size());
    }

    private List<Integer> discount(int discount, List<Film> filmList){
        return filmList.stream()
                .map(film -> film.getPrice() - (discount * film.getPrice()) / 100)
                .collect(Collectors.toList());

    }
}
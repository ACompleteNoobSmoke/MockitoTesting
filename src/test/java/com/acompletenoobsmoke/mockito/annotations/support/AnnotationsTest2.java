package com.acompletenoobsmoke.mockito.annotations.support;

import com.acompletenoobsmoke.mockito.annotations.support.Film.Film;
import com.acompletenoobsmoke.mockito.annotations.support.Film.FilmRepository;
import com.acompletenoobsmoke.mockito.annotations.support.Film.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
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

    private List<Film> listOfFilms() {
        return List.of(
                new Film("12", "Insidious", 400, LocalDate.now()),
                new Film("1", "The Conjuring", 500, LocalDate.now()),
                new Film("3", "Nightmare On Elm Street", 700, LocalDate.now()),
                new Film("5", "Halloween", 300, LocalDate.now())
        );
    }
}

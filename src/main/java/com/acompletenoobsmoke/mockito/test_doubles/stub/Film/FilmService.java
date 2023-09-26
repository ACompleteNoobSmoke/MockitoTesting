package com.acompletenoobsmoke.mockito.test_doubles.stub.Film;

import java.util.List;

public class FilmService {

    private FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void addFilm(Film newFilm) {
        filmRepository.save(newFilm);
    }

    public List<Film> getNewBooksWithAppliedDiscount(int discountRate, int days) {
        List<Film> newFilms = filmRepository.findNewBooks(days);
        newFilms.forEach(film -> {
            int price = film.getPrice();
            int newPrice = price - (discountRate * price) / 100;
            film.setPrice(newPrice);
        });
        return newFilms;
    }

    public int findNumberOfFilms() {
        return filmRepository.findAll().size();
    }


}

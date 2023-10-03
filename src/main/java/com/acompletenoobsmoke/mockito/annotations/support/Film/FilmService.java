package com.acompletenoobsmoke.mockito.annotations.support.Film;

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

    public int calculateTotalCost(List<String> filmIDs) {
        return filmIDs.stream()
                .map(filmRepository::findFilmByID)
                .map(Film::getPrice)
                .reduce(0, Integer::sum);
    }

    public void addFilm(FilmRequest filmRequest) {
        Film newFilm = new Film(null, filmRequest.getTitle(),
                filmRequest.getPrice(), filmRequest.getPublishedDate());
        filmRepository.save(newFilm);
    }

    public int findNumberOfFilms() {
        return filmRepository.findAll().size();
    }


}

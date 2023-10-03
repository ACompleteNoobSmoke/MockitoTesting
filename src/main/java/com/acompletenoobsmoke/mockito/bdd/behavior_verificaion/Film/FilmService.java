package com.acompletenoobsmoke.mockito.bdd.behavior_verificaion.Film;

import java.util.List;

public class FilmService {

    private FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void addFilm(Film film) {
        if(film == null) return;
        if(film.getPrice() <= 500) return;
        filmRepository.save(film);
    }

    public void addFilmRequest(FilmRequest filmRequest) {
        Film newFilm = new Film(null, filmRequest.getTitle(),
                filmRequest.getPrice(), filmRequest.getPublishedDate());
        addFilm(newFilm);
    }

    public void updatePrice(String filmID, int newPrice) {
        if (filmID == null) return;
        Film existingFilm = filmRepository.findFilmByID(filmID);
        if (existingFilm.getPrice() == newPrice) return;
        existingFilm.setPrice(newPrice);
        filmRepository.save(existingFilm);
    }


}

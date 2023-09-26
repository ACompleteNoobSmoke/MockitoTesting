package com.acompletenoobsmoke.mockito.test_doubles.spy.Film;

public class FilmService {

    private FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void addFilm(Film newFilm) {
        filmRepository.save(newFilm);
    }



}

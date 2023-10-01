package com.acompletenoobsmoke.mockito.test_doubles.mock.Film;

public class FilmService {

    private FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void addFilm(Film newFilm) {
        if (newFilm.getPrice() > 400) return;
        filmRepository.save(newFilm);
    }
}

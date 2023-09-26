package com.acompletenoobsmoke.mockito.test_doubles.dummy.Film;

public class FilmService {

    private FilmRepository filmRepository;
    private FilmEmailService filmEmailService;

    public FilmService(FilmRepository filmRepository, FilmEmailService filmEmailService) {
        this.filmRepository = filmRepository;
        this.filmEmailService = filmEmailService;
    }

    public void addFilm(Film newFilm) {
        filmRepository.save(newFilm);
    }

    public int findNumberOfFilms() {
        return filmRepository.findAll().size();
    }


}

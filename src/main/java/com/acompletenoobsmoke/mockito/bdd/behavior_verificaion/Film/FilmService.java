package com.acompletenoobsmoke.mockito.bdd.behavior_verificaion.Film;

public class FilmService {

    private FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void addFilm(Film newFilm) {
        filmRepository.save(newFilm);
    }

    public int findNumberOfFilms() {
        return filmRepository.findAll().size();
    }


}

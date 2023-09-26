package com.acompletenoobsmoke.mockito.test_doubles.spy.Film;

import java.time.LocalDate;

public class Film {
    private String filmID;
    private String title;
    private int price;
    private LocalDate releaseDate;

    public Film(String filmID, String title, int price, LocalDate releaseDate) {
        this.filmID = filmID;
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public String getFilmID() {
        return filmID;
    }

    public void setFilmID(String filmID) {
        this.filmID = filmID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}

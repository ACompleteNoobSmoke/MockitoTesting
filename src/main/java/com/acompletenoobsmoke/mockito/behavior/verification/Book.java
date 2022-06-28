package com.acompletenoobsmoke.mockito.behavior.verification;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private String bookID;
    private String bookTitle;
    private int bookPrice;
    private LocalDate publishedDate;
    private boolean isDigital;

    public Book(){}

    public Book(String bookID, String bookTitle, int bookPrice, LocalDate publishedDate) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookPrice = bookPrice;
        this.publishedDate = publishedDate;
        this.isDigital = false;
    }

    public Book(String bookID, String bookTitle, int bookPrice, LocalDate publishedDate, boolean isDigital) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookPrice = bookPrice;
        this.publishedDate = publishedDate;
        this.isDigital = isDigital;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public boolean getDigital(){ return isDigital; }

    public void setDigital(boolean isDigital){ this.isDigital = isDigital;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookPrice == book.bookPrice && Objects.equals(bookTitle, book.bookTitle) && Objects.equals(publishedDate, book.publishedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookTitle, bookPrice, publishedDate);
    }
}

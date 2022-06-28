package com.acompletenoobsmoke.mockito.argument_matchers;

import java.time.LocalDate;

public class BookRequest {
    private String bookTitle;
    private int bookPrice;
    private LocalDate publishedDate;

    public BookRequest(String bookTitle, int bookPrice, LocalDate publishedDate) {
        this.bookTitle = bookTitle;
        this.bookPrice = bookPrice;
        this.publishedDate = publishedDate;
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
}

package com.acompletenoobsmoke.mockito.behavior.verification;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository {
    List<Book> findNewBooks(int days);
    Book findBookByID(String id);
    Book findBookByTitle(String title);
    Book findBookByTitleAndPublishedDate(String title, LocalDate localDate);
    Book findBookByTitleAndPriceAndIsDigital(String title, int price, boolean isDigital);
    void save(Book newBook);
    void saveAll(List<Book> allBooks);
}

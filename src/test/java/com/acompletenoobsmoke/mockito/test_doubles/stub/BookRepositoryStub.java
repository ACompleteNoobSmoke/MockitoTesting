package com.acompletenoobsmoke.mockito.test_doubles.stub;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryStub implements BookRepository{
    @Override
    public List<Book> findNewBooks(int days) {
        List<Book> newBooks = new ArrayList<>();
        Book book1 = new Book("1234", "The Stand",
                500, LocalDate.now());
        Book book2 = new Book("1235", "Goosebumpz",
                400, LocalDate.now());
        newBooks.add(book1);
        newBooks.add(book2);

        return newBooks;
    }
}

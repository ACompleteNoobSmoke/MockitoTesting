package com.acompletenoobsmoke.mockito.test_doubles.mock;

import com.acompletenoobsmoke.mockito.test_doubles.spy.BookService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockTest {

    @Test
    public void demoMock(){
        BookRepositoryMock bookRepositorySpy = new BookRepositoryMock();
        com.acompletenoobsmoke.mockito.test_doubles.spy.BookService bookService = new com.acompletenoobsmoke.mockito.test_doubles.spy.BookService(bookRepositorySpy);

        com.acompletenoobsmoke.mockito.test_doubles.spy.Book book1 =
                new com.acompletenoobsmoke.mockito.test_doubles.spy.Book("1234", "The Stand",
                500, LocalDate.now());
        com.acompletenoobsmoke.mockito.test_doubles.spy.Book book2 =
                new com.acompletenoobsmoke.mockito.test_doubles.spy.Book("1235", "Goosebumpz",
                400, LocalDate.now());

        bookService.addBook(book1);
        bookService.addBook(book2);

        bookRepositorySpy.verify(book2, 2);
    }
}

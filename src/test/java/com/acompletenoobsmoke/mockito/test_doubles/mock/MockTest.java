package com.acompletenoobsmoke.mockito.test_doubles.mock;

import com.acompletenoobsmoke.mockito.test_doubles.spy.BookService;
import com.acompletenoobsmoke.mockito.test_doubles.stub.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    @Test
    public void demoMockWithMockito(){
        BookRepository bookRepository = mock(BookRepository.class);
        com.acompletenoobsmoke.mockito.test_doubles.mock.BookService bookService
                = new com.acompletenoobsmoke.mockito.test_doubles.mock.BookService(bookRepository);

        com.acompletenoobsmoke.mockito.test_doubles.mock.Book newBook1 =
                new com.acompletenoobsmoke.mockito.test_doubles.mock.Book("1122", "The Bible", 500, LocalDate.now());
        com.acompletenoobsmoke.mockito.test_doubles.mock.Book newBook2 =
                new com.acompletenoobsmoke.mockito.test_doubles.mock.Book("1234", "Preacher", 700, LocalDate.now());

        bookService.addBook(newBook1);
        bookService.addBook(newBook2);

        verify(bookRepository).save(newBook1);
        verify(bookRepository).save(newBook2);
    }
}

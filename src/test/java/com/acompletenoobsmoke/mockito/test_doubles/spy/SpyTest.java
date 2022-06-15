package com.acompletenoobsmoke.mockito.test_doubles.spy;

import com.acompletenoobsmoke.mockito.test_doubles.stub.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SpyTest {

    @Test
    public void demoSpy(){
        BookRepositorySpy bookRepositorySpy = new BookRepositorySpy();
        BookService bookService = new BookService(bookRepositorySpy);

        com.acompletenoobsmoke.mockito.test_doubles.spy.Book book1 =
                new com.acompletenoobsmoke.mockito.test_doubles.spy.Book("1234", "The Stand",
                500, LocalDate.now());
        com.acompletenoobsmoke.mockito.test_doubles.spy.Book book2 =
                new com.acompletenoobsmoke.mockito.test_doubles.spy.Book("1235", "Goosebumpz",
                400, LocalDate.now());

        bookService.addBook(book1);
        bookService.addBook(book2);

        assertEquals(2, bookRepositorySpy.getSaveCalled());
    }

    @Test
    public void lastCalledTest(){
        BookRepositorySpy bookRepositorySpy = new BookRepositorySpy();
        BookService bookService = new BookService(bookRepositorySpy);

        com.acompletenoobsmoke.mockito.test_doubles.spy.Book book1 =
                new com.acompletenoobsmoke.mockito.test_doubles.spy.Book("1234", "The Stand",
                        500, LocalDate.now());
        com.acompletenoobsmoke.mockito.test_doubles.spy.Book book2 =
                new com.acompletenoobsmoke.mockito.test_doubles.spy.Book("1235", "Goosebumpz",
                        400, LocalDate.now());
        com.acompletenoobsmoke.mockito.test_doubles.spy.Book book3 =
                new com.acompletenoobsmoke.mockito.test_doubles.spy.Book("1237", "Dragon Ball Z",
                        600, LocalDate.now());

        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);

        assertTrue(bookRepositorySpy.calledWith(book3));
    }

    @Test
    public void demoSpyWithMockito(){
        BookRepository bookRepositorySpy = spy(BookRepository.class);
        BookService bookService = new BookService(bookRepositorySpy);

        com.acompletenoobsmoke.mockito.test_doubles.spy.Book book1 =
                new com.acompletenoobsmoke.mockito.test_doubles.spy.Book("1234", "The Stand",
                        500, LocalDate.now());
        com.acompletenoobsmoke.mockito.test_doubles.spy.Book book2 =
                new com.acompletenoobsmoke.mockito.test_doubles.spy.Book("1235", "Goosebumpz",
                        400, LocalDate.now());
        com.acompletenoobsmoke.mockito.test_doubles.spy.Book book3 =
                new com.acompletenoobsmoke.mockito.test_doubles.spy.Book("1274", "Oliver Twist",
                        300, LocalDate.now());

        bookService.addBook(book1);
        bookService.addBook(book2);

        verify(bookRepositorySpy, times(1)).save(book2);
        verify(bookRepositorySpy, times(0)).save(book3);
    }
}

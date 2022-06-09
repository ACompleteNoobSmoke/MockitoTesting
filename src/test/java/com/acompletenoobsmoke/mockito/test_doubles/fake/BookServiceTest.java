package com.acompletenoobsmoke.mockito.test_doubles.fake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Test
    public void fakeTest(){
         BookRepository bookRepository = new FakeBookRepository();
         BookService bookService = new BookService(bookRepository);
         bookService.addBook(new Book("1234", "The Stand",
                 250, LocalDate.now()));
        bookService.addBook(new Book("1235", "Goosebumpz",
                200, LocalDate.now()));

        assertEquals(2, bookService.findNumberOfBooks());
    }
}
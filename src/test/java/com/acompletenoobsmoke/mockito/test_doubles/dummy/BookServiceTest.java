package com.acompletenoobsmoke.mockito.test_doubles.dummy;

import com.acompletenoobsmoke.mockito.test_doubles.fake.Book;
import com.acompletenoobsmoke.mockito.test_doubles.fake.BookRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Test
    public void dummyTest(){
        BookRepository bookRepository = new FakeBookRepository();
        EmailService emailService = new DummyEmailService();
        com.acompletenoobsmoke.mockito.test_doubles.dummy.BookService bookService =
                new BookService((com.acompletenoobsmoke.mockito.test_doubles.dummy.BookRepository) bookRepository, emailService);
        bookService.addBook(new com.acompletenoobsmoke.mockito.test_doubles.dummy.Book("1234", "The Stand",
                250, LocalDate.now()));
        bookService.addBook(new com.acompletenoobsmoke.mockito.test_doubles.dummy.Book("1235", "Goosebumpz",
                200, LocalDate.now()));

        assertEquals(2, bookService.findNumberOfBooks());
    }

}
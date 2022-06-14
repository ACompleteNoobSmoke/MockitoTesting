package com.acompletenoobsmoke.mockito.test_doubles.dummy;

import com.acompletenoobsmoke.mockito.test_doubles.fake.BookRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void dummyTestWithMockito(){
        com.acompletenoobsmoke.mockito.test_doubles.dummy.BookRepository bookRepository =
                mock(com.acompletenoobsmoke.mockito.test_doubles.dummy.BookRepository.class);
        EmailService emailService = mock(EmailService.class);

        BookService bookService = new BookService(bookRepository, emailService);
        Book newBook1 = new Book("1122", "The Bible", 500, LocalDate.now());
        Book newBook2 = new Book("1234", "Preacher", 700, LocalDate.now());
        Book newBook3 = new Book("3213", "The Pragmattic Programmer", 800, LocalDate.now());
        Collection<Book> books = new ArrayList<>();
        books.add(newBook1);
        books.add(newBook2);
        books.add(newBook3);

        when(bookRepository.findAll()).thenReturn(books);

        int currentCollectionSize = books.size();

        assertEquals(currentCollectionSize, bookService.findNumberOfBooks());
    }

}
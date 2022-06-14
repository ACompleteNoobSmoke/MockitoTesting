package com.acompletenoobsmoke.mockito.test_doubles.stub;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//import static org.junit.jupiter.api.AssertEquals.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StubTest {

    @Test
    public void demoStub(){
        BookRepository bookRepository = new BookRepositoryStub();
        BookService bookService =  new BookService(bookRepository);

        List<Book> newBooksWithDiscount = bookService.getNewBookWithAppliedDiscount(10, 7);

        assertEquals(2, newBooksWithDiscount.size());
        assertEquals(450, newBooksWithDiscount.get(0).getBookPrice());
        assertEquals(360,newBooksWithDiscount.get(1).getBookPrice());
    }

    @Test
    public void demoStubWithMockito(){
        BookRepository bookRepository = mock(BookRepository.class);
        BookService bookService = new BookService(bookRepository);

        Book newBook1 = new Book("1122", "The Bible", 500, LocalDate.now());
        Book newBook2 = new Book("1234", "Preacher", 700, LocalDate.now());
        Book newBook3 = new Book("3213", "The Pragmattic Programmer", 800, LocalDate.now());
        List<Book> books = new ArrayList<>();
        books.add(newBook1);
        books.add(newBook2);
        books.add(newBook3);

        int discountRate = 10;
        int days = 7;
        int newPrice1 = newBook1.getBookPrice() - (discountRate * newBook1.getBookPrice() / 100);
        int newPrice2 = newBook2.getBookPrice() - (discountRate * newBook2.getBookPrice() / 100);
        int newPrice3 = newBook3.getBookPrice() - (discountRate * newBook3.getBookPrice() / 100);

        when(bookRepository.findNewBooks(days)).thenReturn(books);
        List<Book> resultBooks = bookService.getNewBookWithAppliedDiscount(discountRate, days);

        assertEquals(3, resultBooks.size());
        assertEquals(newPrice1, resultBooks.get(0).getBookPrice());
        assertEquals(newPrice2, resultBooks.get(1).getBookPrice());
        assertEquals(newPrice3, resultBooks.get(2).getBookPrice());
    }
}

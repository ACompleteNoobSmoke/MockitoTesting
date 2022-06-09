package com.acompletenoobsmoke.mockito.test_doubles.stub;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import static org.junit.jupiter.api.AssertEquals.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

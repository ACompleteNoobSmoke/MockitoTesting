package com.acompletenoobsmoke.mockito.stubbing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testCalculateTotalCostOfBooks(){
        List<String> bookIDs = new ArrayList<>();
        bookIDs.add("11223");
        bookIDs.add("11224");
        bookIDs.add("11225");

        Book newBook = new Book("11223", "The Bourne Supremacy", 500, LocalDate.now());
        Book newBook2 = new Book("11224", "Aladdin", 450, LocalDate.now());
        when(bookRepository.findBookByID("11223")).thenReturn(newBook);
        when(bookRepository.findBookByID("11224")).thenReturn(newBook2);

        int total = bookService.calculateTotalCost(bookIDs);

        assertEquals(950, total);
    }

    @Test
    public void testCalculateTotalCostOfBooksDoWhen(){
        List<String> bookIDs = new ArrayList<>();
        bookIDs.add("11223");
        bookIDs.add("11224");
        bookIDs.add("11225");

        Book newBook = new Book("11223", "The Bourne Supremacy", 500, LocalDate.now());
        Book newBook2 = new Book("11224", "Aladdin", 450, LocalDate.now());
        doReturn(newBook).when(bookRepository).findBookByID("11223");
        doReturn(newBook2).when(bookRepository).findBookByID("11224");

        int totalPrice = bookService.calculateTotalCost(bookIDs);

        assertEquals(950, totalPrice);
    }

    @Test
    public void testCalculate2(){
        List<String> bookIDs = new ArrayList<>();
        bookIDs.add("11223");
        bookIDs.add("11224");
        bookIDs.add("11225");

        Book newBook = new Book("11223", "The Bourne Supremacy", 500, LocalDate.now());
        Book newBook2 = new Book("11224", "Aladdin", 450, LocalDate.now());
        Book newBook3 = new Book("11225", "The Dark Tower", 50, LocalDate.now());
        List<Book> returnedBooks = new ArrayList<>();
        returnedBooks.add(newBook);
        returnedBooks.add(newBook2);
        returnedBooks.add(newBook3);
        doReturn(returnedBooks).when(bookRepository).findNewBooks(1);
        int totalPrice = bookService.calculateTotalCost2(bookIDs);

        assertEquals(1000, totalPrice);
    }
}

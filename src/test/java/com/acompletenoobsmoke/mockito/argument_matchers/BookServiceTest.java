package com.acompletenoobsmoke.mockito.argument_matchers;

import com.acompletenoobsmoke.mockito.behavior.verification.Book;
import com.acompletenoobsmoke.mockito.behavior.verification.BookRepository;
import com.acompletenoobsmoke.mockito.behavior.verification.BookRequest;
import com.acompletenoobsmoke.mockito.behavior.verification.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testUpdate(){
        Book newBook1 = new Book("1123", "Everything Is Going To Be Fine", 800, LocalDate.now());
        Book newBook2 = new Book("1123", "Everything Is Going To Be Fine", 1000, LocalDate.now());
        when(bookRepository.findBookByID(any())).thenReturn(newBook1);
        bookService.updatePrice(newBook1.getBookID(), 1000);
        verify(bookRepository).save(newBook2);
    }

    //Argument Matchers should be provided for all arguments
    //Argument Matchers cannot be used outside stubbing or behavior verification

    @Test
    public void testInvalidUseOfArgumentMatchers(){
        Book book = new Book("1134", "Naruto: Shippuden", 1100, LocalDate.now());
        when(bookRepository.findBookByTitleAndPublishedDate(book.getBookTitle(), any(LocalDate.class))).thenReturn(book);
        Book returnBook = bookService.getBookTitleAndPublishedDate(book.getBookTitle(), book.getPublishedDate());
        assertEquals(book.getBookTitle(), returnBook.getBookTitle());
    }

    @Test
    public void testSpecificTypeOfArgumentMatcher(){
        Book book = new Book("1111", "Vampire Hunter D", 800, LocalDate.now(), true);
        when(bookRepository.findBookByTitleAndPriceAndIsDigital(anyString(), anyInt(), anyBoolean())).thenReturn(book);
        Book foundBook = bookService.getBookTitleAndPriceAndIsDigital("Hello", 123, false);
        assertEquals(book.getBookTitle(), foundBook.getBookTitle());
    }

    @Test
    public void testCollectionTypeArgumentMatcher(){
        Book newBook1 = new Book("1111", "Coding Is The Best", 600, LocalDate.now());
        Book newBook2 = new Book("1112", "I Will Become Better", 800, LocalDate.now());
        Book newBook3 = new Book("1113", "Just Be Patient & Work Hard", 900, LocalDate.now());
        List<Book> bookList = Arrays.asList(newBook1, newBook2, newBook3);
        bookService.addBooks(bookList);
        verify(bookRepository).saveAll(anyList());
    }

    @Test
    public void testStringTypeArgumentMatcher(){
        Book newBook = new Book("1111", "David vs Goliath", 900, LocalDate.now());
        when(bookRepository.findBookByTitleAndPublishedDate(startsWith("David"), any(LocalDate.class))).thenReturn(newBook);
        Book foundBook = bookService.getBookTitleAndPublishedDate("David anything", LocalDate.now());
        assertEquals(newBook.getBookTitle(), foundBook.getBookTitle());
    }
}

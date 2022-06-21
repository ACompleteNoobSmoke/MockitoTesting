package com.acompletenoobsmoke.mockito.behavior.verification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public void testAddBook(){
        Book newBook = new Book("1122", "Stephen King's IT",
                700, LocalDate.now());
        bookService.addBook(newBook);
        verify(bookRepository).save(newBook);
    }

    @Test
    public void testAddBookWithGuardCode(){
        Book newBook = new Book("1122", "Stephen King's IT",
                300, LocalDate.now());
        BookRequest newBookRequest = new BookRequest("Stephen King's IT",
                300, LocalDate.now());
        bookService.addBook(newBookRequest);
        verify(bookRepository, never()).save(newBook);
    }

    @Test
    public void testAddBookWithGuardCode1(){
        Book newBook = new Book("1122", "Stephen King's IT",
                700, LocalDate.now());
        BookRequest newBookRequest = new BookRequest("Stephen King's IT",
                700, LocalDate.now());
        bookService.addBook(newBookRequest);
        verify(bookRepository, times(1)).save(newBook);
    }

    @Test
    public void testUpdatePrice(){
        Book newBook = new Book("1155", "Escape From Alcatraz", 500, LocalDate.now());
        when(bookRepository.findBookByID("1155")).thenReturn(newBook);
        bookService.updatePrice("1155", 1000);
        verify(bookRepository).save(newBook);
    }

    @Test
    public void testUpdatePriceNeverSave(){
        //Book newBook = new Book("1155", "Escape From Alcatraz", 500, LocalDate.now());
        when(bookRepository.findBookByID("1155")).thenReturn(null);
        bookService.updatePrice("1155", 1000);
        verify(bookRepository, never()).save(null);
    }

    @Test
    public void testUpdatePriceNoInteraction(){
        bookService.updatePrice(null, 1000);
        verifyNoInteractions(bookRepository);
    }

    @Test
    public void testUpdatePriceNoMoreInteraction(){
        when(bookRepository.findBookByID("1155")).thenReturn(null);
        bookService.updatePrice("1155", 1000);
        verifyNoMoreInteractions(bookRepository);
    }
}

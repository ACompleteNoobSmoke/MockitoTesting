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
}

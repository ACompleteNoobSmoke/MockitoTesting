package com.acompletenoobsmoke.mockito.bdd.behavior_verification;

import com.acompletenoobsmoke.mockito.behavior.verification.Book;
import com.acompletenoobsmoke.mockito.behavior.verification.BookRepository;
import com.acompletenoobsmoke.mockito.behavior.verification.BookRequest;
import com.acompletenoobsmoke.mockito.behavior.verification.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

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

    @Test
    @DisplayName("Given - A Book, When - Updated Price Is Called With New Price, Then - Book Price Is Updated")
    public void testUpdatedBookPrice(){
        //Arrange - Given
        Book newBook = new Book("1111", "Venom", 1233, LocalDate.now());
        given(bookRepository.findBookByID("1111")).willReturn(newBook);

        //Act - When
        bookService.updatePrice(newBook.getBookID(), newBook.getBookPrice());

        //Assert - Then
        then(bookRepository).should().save(newBook);
    }
}

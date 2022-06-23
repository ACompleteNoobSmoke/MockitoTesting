package com.acompletenoobsmoke.mockito.spies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class BookManagerTest {

    @InjectMocks
    private BookManager bookManager;

    @Spy
    private BookService bookService;

    @Test
    public void testMockitoSpy(){
        Book newBook = new Book("1122", "The Future",
                450, LocalDate.now());
        int discountRate = 10;
        doReturn(newBook).when(bookService).findBook("1122");
        int actualDiscount = bookManager.applyDiscountOnBook("1122", discountRate);
        int calculate = bookService.calculate(newBook.getBookPrice(), discountRate);
        assertEquals(calculate, actualDiscount);
    }
}

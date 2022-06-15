package com.acompletenoobsmoke.mockito.annotations.support;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnnotationsTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepositoryMock;

    @Test
    public void testMethodWithMockito(){
        List<Book> booksCreated = new ArrayList<>();
        Book newBook1 = new Book("11223", "IT", 500, LocalDate.now());
        Book newBook2 = new Book("11224", "Oliver Twist", 700, LocalDate.now());
        Book newBook3 = new Book("11225", "The Perks Of Being A Wallflower", 800, LocalDate.now());
        Book newBook4 = new Book("11226", "The Dark Knight Returns", 300, LocalDate.now());
        booksCreated.add(newBook1);
        booksCreated.add(newBook2);
        booksCreated.add(newBook3);
        booksCreated.add(newBook4);

        int discoutRate = 10;
        int discount1 = bookService.getDiscountPrice(discoutRate, newBook1);
        int discount2 = bookService.getDiscountPrice(discoutRate, newBook2);
        int discount3 = bookService.getDiscountPrice(discoutRate, newBook3);
        int discount4 = bookService.getDiscountPrice(discoutRate, newBook4);

        when(bookRepositoryMock.findNewBooks(8)).thenReturn(booksCreated);

        List<Book> discountedListOfBooks = bookService.getNewBookWithAppliedDiscount(discoutRate, 8);

        assertEquals(booksCreated.size(), discountedListOfBooks.size());
        assertEquals(discount1, booksCreated.get(0).getBookPrice());
        assertEquals(discount2, booksCreated.get(1).getBookPrice());
        assertEquals(discount3, booksCreated.get(2).getBookPrice());
        assertEquals(discount4, booksCreated.get(3).getBookPrice());

    }
}

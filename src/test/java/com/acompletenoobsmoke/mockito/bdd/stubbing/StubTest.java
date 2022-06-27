package com.acompletenoobsmoke.mockito.bdd.stubbing;

import com.acompletenoobsmoke.mockito.test_doubles.stub.BookRepository;
import com.acompletenoobsmoke.mockito.test_doubles.stub.BookRepositoryStub;
import com.acompletenoobsmoke.mockito.test_doubles.stub.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StubTest {

    @InjectMocks
    private com.acompletenoobsmoke.mockito.bdd.stubbing.BookService bookService;

    @Mock
    private com.acompletenoobsmoke.mockito.bdd.stubbing.BookRepository bookRepository;


    @Test
    public void demoStubWithMockito() {

        com.acompletenoobsmoke.mockito.bdd.stubbing.Book newBook1 = new com.acompletenoobsmoke.mockito.bdd.stubbing.Book("1122", "The Bible", 500, LocalDate.now());
        com.acompletenoobsmoke.mockito.bdd.stubbing.Book newBook2 = new com.acompletenoobsmoke.mockito.bdd.stubbing.Book("1234", "Preacher", 700, LocalDate.now());
        com.acompletenoobsmoke.mockito.bdd.stubbing.Book newBook3 = new com.acompletenoobsmoke.mockito.bdd.stubbing.Book("3213", "The Pragmattic Programmer", 800, LocalDate.now());
        List<com.acompletenoobsmoke.mockito.bdd.stubbing.Book> books = new ArrayList<>();
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

    @Test
    @DisplayName("Given New Books - When Get New Books With Applied Discount Is Called - Then New Books With Applied Discount Is Returned")
    public void test_Given_NewBooks_When_GetNewBooksWithAppliedDiscountIsCalled_Then_NewBooksWithAppliedDiscountIsReturned() {
        //Arrange - Given
        int discountPrice = 10;
        Book book1 = new Book("1122", "Mockito In Action", 2100, LocalDate.now());
        Book book2 = new Book("1111", "The Dark Knight Returns", 1000, LocalDate.now());
        Book book3 = new Book("1212", "DBZ: Namek Saga", 2000, LocalDate.now());
        List<Book> books = Arrays.asList(book1, book2, book3);
        given(bookRepository.findNewBooks(7)).willReturn(books);
        int price1 = bookService.calculate(book1.getBookPrice(), discountPrice);
        int price2 = bookService.calculate(book2.getBookPrice(), discountPrice);
        int price3 = bookService.calculate(book3.getBookPrice(), discountPrice);


        //Act - When
        List<Book> newBookWithAppliedDiscount = bookService.getNewBookWithAppliedDiscount(discountPrice, 7);

        //Asses - Then
//        assertEquals(3, newBookWithAppliedDiscount.size());
//        assertEquals(price1, newBookWithAppliedDiscount.get(0).getBookPrice());
//        assertEquals(price2, newBookWithAppliedDiscount.get(1).getBookPrice());
//        assertEquals(price3, newBookWithAppliedDiscount.get(2).getBookPrice());

        then(newBookWithAppliedDiscount).isNotNull();
        then(newBookWithAppliedDiscount.size()).isEqualTo(3);
        then(newBookWithAppliedDiscount.get(0).getBookPrice()).isEqualTo(price1);
    }
}

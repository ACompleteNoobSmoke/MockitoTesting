package com.acompletenoobsmoke.mockito.annotations.support;

import com.acompletenoobsmoke.mockito.annotations.support.Book;
import com.acompletenoobsmoke.mockito.test_doubles.stub.BookRepository;

import java.util.List;

public class BookService {

    private com.acompletenoobsmoke.mockito.annotations.support.BookRepository bookRepository;

    public BookService(com.acompletenoobsmoke.mockito.annotations.support.BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getNewBookWithAppliedDiscount(int discountRate, int days){
        List<Book> newBooks = bookRepository.findNewBooks(days);

        for(Book book: newBooks){
            int price = book.getBookPrice();
            int newPrice = price - (discountRate * price / 100);
            book.setBookPrice(newPrice);
        }

        return newBooks;
    }

    public int getDiscountPrice(int discount, Book discountBook){
        if(discountBook == null) return 0;
        if(discount == 0) return discountBook.getBookPrice();
        int price = discountBook.getBookPrice();
        return price - (discount * price / 100);
    }


}

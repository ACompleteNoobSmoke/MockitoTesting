package com.acompletenoobsmoke.mockito.bdd.stubbing;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getNewBookWithAppliedDiscount(int discountRate, int days){
        List<Book> newBooks = bookRepository.findNewBooks(days);

        for(Book book: newBooks){
            int price = book.getBookPrice();
            int newPrice = calculate(price, discountRate);
            book.setBookPrice(newPrice);
        }

        return newBooks;
    }

    public int calculate(int price, int discountRate){
        return price - (discountRate * price/100);
    }


}

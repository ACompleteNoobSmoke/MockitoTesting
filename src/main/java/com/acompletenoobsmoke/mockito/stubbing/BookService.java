package com.acompletenoobsmoke.mockito.stubbing;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
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

    public int calculateTotalCost2(List<String> bookIDs){
        return bookRepository.findNewBooks(1).stream()
                .filter(book -> bookIDs.contains(book.getBookID()))
                .mapToInt(Book::getBookPrice).sum();
    }

    public int calculateTotalCost(List<String> bookIDs){
        int total = 0;
        for(String id: bookIDs){
            Book book = bookRepository.findBookByID(id);
            if(book != null) total += book.getBookPrice();
        }
        return total;
    }


}

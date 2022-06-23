package com.acompletenoobsmoke.mockito.spies;


public class BookService {

    public Book findBook(String bookID){
        throw new RuntimeException("Method Not Yet Implemented");
    }

    public int getAppliedDiscount(Book book, int discountRate){
        int price = book.getBookPrice();
        return calculate(price, discountRate);
    }

    public int calculate(int price, int discountRate){
        return price - (price * discountRate/100);
    }

}

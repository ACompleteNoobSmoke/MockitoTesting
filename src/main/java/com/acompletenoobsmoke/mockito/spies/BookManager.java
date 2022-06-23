package com.acompletenoobsmoke.mockito.spies;

public class BookManager {

    private BookService bookService;

    public BookManager(BookService bookService){this.bookService = bookService;}

    public int applyDiscountOnBook(String bookID, int discountRate){
        if(bookID == null) return 0;
        Book foundBook = bookService.findBook(bookID);
        if(foundBook == null) return 0;
        int discountedPrice = bookService.getAppliedDiscount(foundBook, discountRate);
        return discountedPrice;
    }
}

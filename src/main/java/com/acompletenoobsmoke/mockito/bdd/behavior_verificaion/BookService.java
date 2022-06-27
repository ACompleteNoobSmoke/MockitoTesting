package com.acompletenoobsmoke.mockito.bdd.behavior_verificaion;

import com.acompletenoobsmoke.mockito.behavior.verification.BookRequest;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public void updatePrice(String bookID, int updatedPrice){
        if(bookID != null){
            Book foundBook = bookRepository.findBookByID(bookID);
            if(foundBook == null) return;
            foundBook.setBookPrice(updatedPrice);
            bookRepository.save(foundBook);
        }
    }
}

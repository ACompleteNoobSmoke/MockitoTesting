package com.acompletenoobsmoke.mockito.behavior.verification;

import com.acompletenoobsmoke.mockito.stubbing.BookRequest;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public void addBook(Book newBook){
        if(newBook == null) return;
//        if(bookRepository.
//                findBookByID(newBook.getBookID()) == null &&
//                bookRepository.findBookByTitle(newBook.getBookTitle()) == null)
//            return;
        System.out.println("I will become better");
        bookRepository.save(newBook);
    }
}

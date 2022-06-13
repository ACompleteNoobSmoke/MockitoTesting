package com.acompletenoobsmoke.mockito.test_doubles.spy;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public void addBook(Book newBook){
        bookRepository.save(newBook);
    }
}

package com.acompletenoobsmoke.mockito.exception_handling;

import java.sql.SQLException;
import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public int getTotalPriceOfBooks(){
        List<Book> allBooks = getAllBooks();
        if(allBooks == null || allBooks.isEmpty()) return 0;
        return allBooks.stream().mapToInt(Book::getBookPrice).sum();
    }

    public void addBook(Book newBook){
        if(newBook == null) return;
        try{bookRepository.save(newBook);}
        catch (SQLException e){ throw new
                DatabaseWriteException("Unable To Write To Database Due To " + e.getMessage());}
    }

    private List<Book> getAllBooks(){
        List<Book> allBooks = null;
        try {allBooks = bookRepository.getAllBooks();}
        catch (SQLException e) {throw new
                DatabaseReadException("Unable To Read Database Due To " + e.getMessage());}
        return allBooks;
    }
}

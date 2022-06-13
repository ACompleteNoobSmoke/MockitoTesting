package com.acompletenoobsmoke.mockito.test_doubles.mock;

import com.acompletenoobsmoke.mockito.test_doubles.spy.Book;
import com.acompletenoobsmoke.mockito.test_doubles.spy.BookRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRepositoryMock implements BookRepository {

    private int saveCalled = 0;
    private Book lastSavedBook = null;

    @Override
    public void save(Book book) {
        saveCalled++;
        lastSavedBook = book;
    }

  public void verify(Book newBook, int called){
        assertEquals(called, saveCalled);
        assertTrue(lastSavedBook.equals(newBook));
  }
}

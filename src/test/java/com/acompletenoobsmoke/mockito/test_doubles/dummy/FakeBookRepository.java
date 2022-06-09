package com.acompletenoobsmoke.mockito.test_doubles.dummy;

import com.acompletenoobsmoke.mockito.test_doubles.fake.Book;
import com.acompletenoobsmoke.mockito.test_doubles.fake.BookRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FakeBookRepository implements BookRepository {

    Map<String, Book> bookStore  = new HashMap<>();

    @Override
    public void save(Book book) {
        bookStore.put(book.getBookID(), book);
    }

    @Override
    public Collection<Book> findAll() {
        return bookStore.values();
    }
}

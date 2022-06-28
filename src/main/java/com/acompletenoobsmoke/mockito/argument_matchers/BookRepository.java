package com.acompletenoobsmoke.mockito.argument_matchers;

import java.util.List;

public interface BookRepository {
    List<Book> findNewBooks(int days);
    Book findBookByID(String id);
    Book findBookByTitle(String title);
    void save(Book newBook);
}

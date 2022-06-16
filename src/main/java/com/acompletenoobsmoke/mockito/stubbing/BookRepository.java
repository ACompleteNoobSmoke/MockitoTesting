package com.acompletenoobsmoke.mockito.stubbing;

import java.util.List;

public interface BookRepository {
    List<Book> findNewBooks(int days);
    Book findBookByID(String id);
}

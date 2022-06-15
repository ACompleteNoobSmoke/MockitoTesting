package com.acompletenoobsmoke.mockito.annotations.support;

import com.acompletenoobsmoke.mockito.annotations.support.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findNewBooks(int days);
}

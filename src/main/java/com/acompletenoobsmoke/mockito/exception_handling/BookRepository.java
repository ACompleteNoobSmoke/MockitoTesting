package com.acompletenoobsmoke.mockito.exception_handling;

import java.sql.SQLException;
import java.util.List;

public interface BookRepository {

    List<Book> getAllBooks() throws SQLException;
}

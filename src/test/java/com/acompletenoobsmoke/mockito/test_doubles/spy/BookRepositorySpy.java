package com.acompletenoobsmoke.mockito.test_doubles.spy;

public class BookRepositorySpy implements BookRepository{

    private int saveCalled = 0;
    private Book lastSavedBook = null;

    @Override
    public void save(Book book) {
        saveCalled++;
        lastSavedBook = book;
    }

    public int getSaveCalled(){ return saveCalled; }

    public Book getLastSavedBook(){ return lastSavedBook; }

    public boolean calledWith(Book book){
        return lastSavedBook.equals(book);
    }
}

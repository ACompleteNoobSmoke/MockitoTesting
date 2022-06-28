package com.acompletenoobsmoke.mockito.argument_matchers;

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

    public void addBook(BookRequest bookRequest){
        if(bookRequest == null) return;
        if(bookRequest.getBookPrice() <= 500) return;
        Book newBook = new Book();
        newBook.setBookTitle(bookRequest.getBookTitle());
        newBook.setBookPrice(bookRequest.getBookPrice());
        newBook.setPublishedDate(bookRequest.getPublishedDate());
        bookRepository.save(newBook);
    }

    public void updatePrice(String bookID, int updatedPrice){
        if(bookID != null){
            Book foundBook = bookRepository.findBookByID(bookID);
            if(foundBook == null) return;
            foundBook.setBookPrice(updatedPrice);
            bookRepository.save(foundBook);
        }
    }
}

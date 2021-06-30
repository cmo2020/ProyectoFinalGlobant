package com.Book.projectBook.Service;


import com.Book.projectBook.Model.Book;


import java.util.List;
import java.util.Optional;

public interface BookServiceInterface {

     Book createBook(Book book);

     Book updateBook(Book book);

     String deleteById(Long id);

     List<Book> listBook();

     Optional<Book> getBookById(Book book);

     List<Book> listAvailable();

     List<Book> listReserved();
}

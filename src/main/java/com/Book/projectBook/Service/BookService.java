package com.Book.projectBook.Service;

import com.Book.projectBook.Exception.ExceptionBookExists;
import com.Book.projectBook.Model.Book;
import com.Book.projectBook.Model.Booking;
import com.Book.projectBook.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class BookService implements BookServiceInterface {



    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book findByTitle(String title) {
       return bookRepository.findByTitle(title);
    }


    @Override
    public Book createBook(Book book) {
        Book existingBook = bookRepository.findByTitle(book.getTitle());
        if (existingBook != null) {
            throw new ExceptionBookExists("This book already exists");
        }
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        if (optionalBook.isPresent()){
        Book updateBook = optionalBook.get();
        updateBook.setTitle(book.getTitle());
        updateBook.setAuthor(book.getAuthor());
        updateBook.setPublishedDate(book.getPublishedDate());
        bookRepository.save(updateBook);}
        return bookRepository.findById(book.getId()).get();
    }

      @Override
        public String deleteById(Long id) {
        bookRepository.deleteById(id);
        return "Book removed \n" + "IdBook:" + id;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> listBook() {
        return (List<Book>) bookRepository.findByOrderByTitleAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> listAvailable() {
        return (List<Book>) bookRepository.findByBookingIsNull();
    }

    @Override
    public List<Book> listReserved() {
        return (List<Book>) bookRepository.findByBookingNotNull();
    }

    public String getStatus(Booking booking) {
        if (booking == null) { return "available"; }
        else {  return "reserved";
        }
    }

    @Override
    public Optional<Book> getBookById(Book book) {
        return bookRepository.findById(book.getId());
    }


}



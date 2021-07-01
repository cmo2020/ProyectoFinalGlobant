package com.Book.projectBook.Controller;

import com.Book.projectBook.Model.Book;
import com.Book.projectBook.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        bookController = new BookController(bookService);
    }

    @Test
     void listBook()  throws Exception {
        Date publishedDate = new Date();
        Book [] bookArray = new Book[]{
                new Book ( 1L, "Title1", "Author1", publishedDate),
                new Book(2L, "Title2", "Author2", publishedDate),
                new Book(3L, "Title3", "Author3", publishedDate)
        };
        List <Book> bookList = new ArrayList<>(Arrays.asList(bookArray));

        when(bookService.listBook()).thenReturn(bookList);

        List <Book> result = bookController.listBook();

        verify(bookService, times(1)).listBook();

        assertThat(result).isEqualTo(bookList);
    }

    @Test
    void listAvailable() {
        Date publishedDate = new Date();
        Book [] bookArray = new Book[]{
                new Book ( 1L, "Title1", "Author1", publishedDate),
                new Book(2L, "Title2", "Author2", publishedDate),
                new Book(3L, "Title3", "Author3", publishedDate)
        };
        List <Book> bookList = new ArrayList<>(Arrays.asList(bookArray));

        when(bookService.listAvailable()).thenReturn(bookList);

        List <Book> result = bookController.listAvailable();

        verify(bookService, times(1)).listAvailable();

        assertThat(result).isEqualTo(bookList);
    }

    @Test
    void listReserved() {
        Date publishedDate = new Date();
        Book [] bookArray = new Book[]{
                new Book ( 1L, "Title1", "Author1", publishedDate),
                new Book(2L, "Title2", "Author2", publishedDate),
                new Book(3L, "Title3", "Author3", publishedDate)
        };
        List <Book> bookList = new ArrayList<>(Arrays.asList(bookArray));

        when(bookService.listReserved()).thenReturn(bookList);

        List <Book> result = bookController.listReserved();

        verify(bookService, times(1)).listReserved();

        assertThat(result).isEqualTo(bookList);
    }

    @Test
    @Disabled
    void createBook() throws Exception {
//        Book book = new Book(1L, "Harry Potter", "ANon", new Date());
//        ResponseEntity<Book> statusCode = new ResponseEntity<Book> (HttpStatus.CREATED);
//
//        when(bookService.createBook(book), .thenReturn(statusCode);
//
//
//        Book result = bookService.createBook(book);
//
//        verify(bookService, times(1)).createBook(book);
//
//        assertThat(result).isEqualTo(book);
//        assertThat(result).isEqualTo(statusCode);
    }

    @Test
    void getBookById() throws Exception{

        Date date = new Date(2020, 1, 1);
        Book book = new Book(1L, "Yoda", "SomeBody", date);

        when(bookService.getBookById(book)).thenReturn(Optional.of(book));

        Optional<Book> result = bookController.getBookById(book);

        verify(bookService, times(1)).getBookById(book);

        assertThat(result).isEqualTo(Optional.of(book));

    }

    @Test
    @Disabled
    void updateBook() throws Exception{

//        Date publishedDate = new Date(2000, 1, 1);
//        Book originalBook = new Book(1L, "Harry Potter", "ANon", publishedDate);
//
//        Date newDate = new Date(2020, 1, 1);
//        Book newBook = new Book(1L, "Yoda", "SomeBody", newDate);
//
//        when(bookService.updateBook(newBook)).thenReturn(originalBook);
//
//        Book resultBook = bookController.updateBook(newBook);
//
//        verify(bookService, times(2)).updateBook(originalBook);
//
//        assertThat(resultBook.getTitle()).isEqualTo("YODA");
//        assertThat(resultBook.getAuthor()).isEqualTo("SOMEBODY");
//        assertThat(resultBook.getPublishedDate()).isEqualTo(newDate);




    }

    @Test
    void deleteBookById() {

//        String result = bookService.deleteById(1L);
//
//        when(bookService.deleteById(1L)).thenReturn(result);
//
//        verify(bookController, times(1)).deleteBookById(1L);
//
//        assertThat(result).isEqualTo(1L);
//

    }


}
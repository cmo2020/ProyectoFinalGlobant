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
     void testListBook()  throws Exception {
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
    void testListAvailable() {
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
    void testListReserved() {
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
    void testCreateBook() throws Exception {

        Book book = new Book(1L, "Harry Potter", "ANon", new Date());

        ResponseEntity<Book> result = bookController.createBook(book);


        verify(bookService, times(1)).createBook(book);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    void testGetBookById() throws Exception{

        Date date = new Date(2020, 1, 1);
        Book book = new Book(1L, "Yoda", "SomeBody", date);

        when(bookService.getBookById(book)).thenReturn(Optional.of(book));

        Optional<Book> result = bookController.getBookById(book);

        verify(bookService, times(1)).getBookById(book);

        assertThat(result).isEqualTo(Optional.of(book));

    }

    @Test
    void testUpdateBook() throws Exception{

        Date publishedDate = new Date(2000, 1, 1);
        Book book = new Book(1L, "Harry Potter", "Anon", publishedDate);

        when(bookService.updateBook(book)).thenReturn(book);

        Book result = bookController.updateBook(book);

        verify(bookService, times(1)).updateBook(book);

        assertThat(result.getTitle()).isEqualTo("Harry Potter");
        assertThat(result.getAuthor()).isEqualTo("Anon");
        assertThat(result.getPublishedDate()).isEqualTo(publishedDate);




    }

    @Test
    void testDeleteBookById() {

        String result = bookController.deleteBookById(1L);


        verify(bookService, times(1)).deleteById(1L);

        assertThat(result).isEqualTo(null);


    }


}
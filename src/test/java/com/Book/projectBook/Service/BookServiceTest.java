package com.Book.projectBook.Service;

import com.Book.projectBook.Exception.ExceptionBookExists;
import com.Book.projectBook.Model.Book;

import com.Book.projectBook.Model.Booking;
import com.Book.projectBook.Repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {


    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookRepository);
    }


    @Test
    void canFindByTitle() {

        Book book = new Book(1L, "Harry Potter", "ANon", new Date());

        when(bookRepository.findByTitle(book.getTitle())).thenReturn(book);

        String title = "Harry Potter";

        Book found = bookService.findByTitle(title);

        assertThat(found.getTitle()).isEqualTo(title);


    }


    @Test
    void canCreateBook() throws ExceptionBookExists {

        Book book = new Book(1L, "Harry Potter", "ANon", new Date());

        when(bookRepository.findByTitle(any())).thenReturn(null);

        Book bookCreated = bookService.createBook(book);

        verify(bookRepository, times(1)).save(book);


    }

    @Test
    void cantCreateBook() throws ExceptionBookExists {

        Book book = new Book(1L, "Harry Potter", "ANon", new Date());

        when(bookRepository.findByTitle(book.getTitle())).thenReturn(book);

        Assertions.assertThrows(ExceptionBookExists.class, () -> {
            Book bookCreated = bookService.createBook(book);
        });

    }


    @Test
    void canUpdateBook() {
        Date publishedDate = new Date(2000, 1, 1);
        Book originalBook = new Book(1L, "Harry Potter", "ANon", publishedDate);

        Date newDate = new Date(2020, 1, 1);
        Book newBook = new Book(1L, "Yoda", "SomeBody", newDate);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(originalBook));

        Book resultBook = bookService.updateBook(newBook);

        verify(bookRepository, times(2)).findById(1L);
        verify(bookRepository, times(1)).save(originalBook);

        assertThat(resultBook.getTitle()).isEqualTo("YODA");
        assertThat(resultBook.getAuthor()).isEqualTo("SOMEBODY");
        assertThat(resultBook.getPublishedDate()).isEqualTo(newDate);


    }


    @Test
    void deleteById() {

        String result = bookService.deleteById(1L);

        verify(bookRepository, times(1)).deleteById(1L);

        assertThat(result).isEqualTo("Book removed \n" + "IdBook:" + 1L);

    }

    @Test
    void listBook() {
        Date publishedDate = new Date();
        Book [] bookArray = new Book[]{
                new Book ( 1L, "Title1", "Author1", publishedDate),
                new Book(2L, "Title2", "Author2", publishedDate),
                new Book(3L, "Title3", "Author3", publishedDate)
        };
        List <Book> bookList = new ArrayList<>(Arrays.asList(bookArray));

        when(bookRepository.findByOrderByTitleAsc()).thenReturn(bookList);

        List <Book> result = bookService.listBook();

        verify(bookRepository, times(1)).findByOrderByTitleAsc();

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

        when(bookRepository.findByBookingIsNull()).thenReturn(bookList);

        List <Book> result = bookService.listAvailable();

        verify(bookRepository, times(1)).findByBookingIsNull();

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

        when(bookRepository.findByBookingNotNull()).thenReturn(bookList);

        List <Book> result = bookService.listReserved();

        verify(bookRepository, times(1)).findByBookingNotNull();

        assertThat(result).isEqualTo(bookList);


    }

    @Test
    void getStatus() {
        Booking newBooking = new Booking();

        String bookingCreated = bookService.getStatus(newBooking);

        assertThat(bookingCreated).isEqualTo("reserved");
        assertThat(bookingCreated).isNotEqualTo("available");


    }

    @Test
    void getBookById() {

        Date date = new Date(2020, 1, 1);
        Book book = new Book(1L, "Yoda", "SomeBody", date);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> bookObtained = bookService.getBookById(book);

        verify(bookRepository, times(1)).findById(1L);

        assertThat(bookObtained).isEqualTo(Optional.of(book));


    }
}
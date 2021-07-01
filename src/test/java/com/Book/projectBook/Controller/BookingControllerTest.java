package com.Book.projectBook.Controller;

import com.Book.projectBook.Exception.ExceptionBookExists;
import com.Book.projectBook.Model.Book;
import com.Book.projectBook.Model.Booking;
import com.Book.projectBook.Service.BookService;
import com.Book.projectBook.Service.BookingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        bookingController = new BookingController(bookingService);
    }


    @Test
    void listBooking() {

        Date startDate = new Date();
        Date endDate = new Date();
        Booking[] bookingArray = new Booking[]{
                new Booking ( 1L, startDate, endDate),
                new Booking(2L, startDate, endDate),
                new Booking (3L, startDate, endDate)
        };

        List<Booking> bookingList = new ArrayList<>(Arrays.asList(bookingArray));

        when(bookingService.listBooking()).thenReturn(bookingList);

        List <Booking> result = bookingController.listBooking();

        verify(bookingService, times(1)).listBooking();

        assertThat(result).isEqualTo(bookingList);
    }

    @Test
    @Disabled
    void createBooking() {


        ;
    }

    @Test
    void getBookingById() {

        Date startDate = new Date();
        Date endDate = new Date();
        Booking booking = new Booking (3L, startDate, endDate);

        when(bookingService.getBookingById(booking)).thenReturn(Optional.of(booking));

        Optional<Booking> result = bookingController.getBookingById(booking);

        verify(bookingService, times(1)).getBookingById(booking);

        assertThat(result).isEqualTo(Optional.of(booking));

    }

    @Test
    @Disabled
    void deleteBookingById() {
    }

    @Test
    @Disabled
    void updateBooking() {
    }
}
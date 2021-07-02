package com.Book.projectBook.Service;

import com.Book.projectBook.Model.Booking;
import com.Book.projectBook.Repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService underTest;

    @BeforeEach
    void setUp() { underTest = new BookingService(bookingRepository);
    }

    @Test
    @Disabled
    void createBooking() {
    }

    @Test
    void canUpdateBooking() {

        //given
        Date publishedDate = new Date(2000, 1, 1);
        Booking bookingMock = new Booking(1L,publishedDate,publishedDate);

        given(bookingRepository.findById(anyLong())).willReturn(Optional.of(bookingMock));

        //when
        Booking bookingUpdate = new Booking(1L,publishedDate,publishedDate);
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(bookingUpdate));

        //then
        Booking bookingActual = underTest.updateBooking(bookingUpdate);

        verify(bookingRepository).save(bookingActual);
        verify(bookingRepository, times(2)).findById(any());

        assertThat(bookingActual.getIdBooking()).isEqualTo(1L);
    }

    @Test
    void canDeleteByIdBooking() {
        String result = underTest.deleteByIdBooking(1L);

        verify(bookingRepository, times(1)).deleteById(1L);

        assertThat(result).isEqualTo("Booking removed \n" + "IdBooking:" + 1L);
    }

    @Test
    void canListBooking() {
        Date publishedDate = new Date();
        Booking [] bookingArray = new Booking[]{
                new Booking ( 1L, publishedDate, publishedDate),
                new Booking(2L, publishedDate, publishedDate),
                new Booking(3L, publishedDate, publishedDate)
        };
        List<Booking> listBooking = new ArrayList<>(Arrays.asList(bookingArray));

        when(bookingRepository.findAll()).thenReturn(listBooking);

        List<Booking> result = underTest.listBooking();

        verify(bookingRepository, times(1)).findAll();

        assertThat(result).isEqualTo(listBooking);
    }

    @Test
    void canGetBookingById() {
        Date publishedDate = new Date(2000, 1, 1);
        Booking booking = new Booking(1L, publishedDate, publishedDate);

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        Optional<Booking> bookingObtained = underTest.getBookingById(booking);

        verify(bookingRepository, times(1)).findById(1L);
    }
}
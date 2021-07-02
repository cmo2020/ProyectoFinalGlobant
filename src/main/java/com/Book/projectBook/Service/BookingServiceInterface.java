package com.Book.projectBook.Service;


import com.Book.projectBook.Model.Booking;
import java.util.List;
import java.util.Optional;

public interface BookingServiceInterface {

     Booking createBooking(Booking booking);

     Booking updateBooking(Booking booking);

     String deleteByIdBooking(Long idBooking);

     List<Booking> listBooking();

     Optional<Booking> getBookingById(Booking booking);

}

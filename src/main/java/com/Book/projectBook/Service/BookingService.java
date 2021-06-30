package com.Book.projectBook.Service;

import com.Book.projectBook.Model.Book;
import com.Book.projectBook.Model.Booking;
import com.Book.projectBook.Model.User;
import com.Book.projectBook.Repository.BookRepository;
import com.Book.projectBook.Repository.BookingRepository;
import com.Book.projectBook.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.util.*;

@Service
public class BookingService implements BookingServiceInterface{


    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;



    @Override
    public Booking createBooking(Booking booking) {
        Optional<Book> optionalBook = bookRepository.findById(booking.getBook().getId());
        Optional<User> optionalUser = userRepository.findById(booking.getUser().getId());
        Book book = optionalBook.get();
        User user = optionalUser.get();
        booking.setBook(book);
        booking.setUser(user);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        Optional<Booking> optionalBooking = bookingRepository.findById(booking.getIdBooking());
        if(optionalBooking.isPresent()){
        Booking updatedBooking = optionalBooking.get();
        updatedBooking.setStartDate(booking.getStartDate());
        updatedBooking.setEndDate(booking.getEndDate());
        bookingRepository.save(updatedBooking);}
        return bookingRepository.findById(booking.getIdBooking()).get();
    }

    @Override
    public String deleteByIdBooking(Long idBooking) {
        bookingRepository.deleteById(idBooking);
        return "Booking removed \n" + "IdBooking:" + idBooking;

    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> listBooking() {
       return (List<Booking>)bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getBookingById(Booking booking) {
        return bookingRepository.findById(booking.getIdBooking());
    }

}

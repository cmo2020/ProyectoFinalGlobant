package com.Book.projectBook.Controller;


import com.Book.projectBook.Model.Book;
import com.Book.projectBook.Model.Booking;
import com.Book.projectBook.Model.User;
import com.Book.projectBook.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

//    @GetMapping("/listBooking")
//    public List<Booking> listBooking() {
//        return bookingService.listBooking();
//    }

    @PostMapping("/createBooking")
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking) {
        bookingService.createBooking(booking);
        return new ResponseEntity<Booking>(HttpStatus.CREATED);
    }

//    @RequestMapping("/getBookingById/{bookingId}")
//    public Optional<Booking> getBookingById(@PathVariable("bookingId") Booking booking) {
//        return bookingService.getBookingById(booking);
//
//    }

    @DeleteMapping("/deleteBookingById/{bookingId}")
    public String deleteBookingById(@PathVariable("bookingId") Long idBooking) {
        return bookingService.deleteByIdBooking(idBooking);
    }

    @PutMapping("/updateBooking/{bookingId}")
    public Booking updateBooking(@RequestBody Booking booking) {
        return bookingService.updateBooking(booking);
    }

}

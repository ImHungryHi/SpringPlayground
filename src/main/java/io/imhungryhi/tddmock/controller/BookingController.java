package io.imhungryhi.tddmock.controller;

import io.imhungryhi.tddmock.dao.interfaces.BookingService;
import io.imhungryhi.tddmock.helper.BookingList;
import io.imhungryhi.tddmock.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @GetMapping
    public ResponseEntity<?> getBookings() throws Exception {
        BookingList bookingList = new BookingList(bookingService.getAll());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(bookingList, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingByName(@PathVariable int id) throws Exception {
        Booking booking = bookingService.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(booking, headers, HttpStatus.OK);
    }
}

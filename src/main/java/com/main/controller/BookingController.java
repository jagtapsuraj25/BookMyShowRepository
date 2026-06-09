package com.main.controller;

import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.BookingDto;
import com.main.entity.Booking;
import com.main.entity.Show;
import com.main.repository.ShowRepository;
import com.main.service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final ShowRepository showRepository;
	
	@Autowired
	BookingService bookingService;

    BookingController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }
	
	@PostMapping("/createBooking")
	public ResponseEntity<Booking> createBooking(@RequestBody BookingDto bookingDto)
	{
		
		return ResponseEntity.ok(bookingService.createBooking(bookingDto)) ;
	}
	
	@GetMapping("getUserBookings/{id}")
	public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long id){
		
		return ResponseEntity.ok(bookingService.getUserBookings(id));
	}
	
	@GetMapping("getShowBookings/{id}")
	public ResponseEntity<List<Booking>> getShowBookings(@PathVariable Long id){
		
		return ResponseEntity.ok(bookingService.getShowBookings(id));
	}
}

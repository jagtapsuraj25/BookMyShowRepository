package com.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.main.dto.BookingDto;
import com.main.entity.Booking;

public interface BookingService {
	
	public Booking createBooking(BookingDto bookingDto);
	
	public List<Booking> getUserBookings(Long id);
	
	public List<Booking> getShowBookings(Long id);
    
	public Booking confirmBooking(Long id);

	public Booking cancelBooking(Long id);
		
		
		
	
}
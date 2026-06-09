package com.main.serviceIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.BookingDto;
import com.main.entity.Booking;
import com.main.entity.BookingStatus;
import com.main.repository.BookingRepository;
import com.main.service.BookingService;

@Service
public class BookingServiceIMPL implements BookingService{

	@Autowired
	BookingRepository bookingRepository;
	
	@Override
	public Booking createBooking(BookingDto bookingDto) {
		
		return null;
	}

	@Override
	public List<Booking> getUserBookings(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getShowBookings(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking confirmBooking(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking cancelBooking(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getBookingByStatus(BookingStatus bookingStatus) {
		// TODO Auto-generated method stub
		return null;
	}

}

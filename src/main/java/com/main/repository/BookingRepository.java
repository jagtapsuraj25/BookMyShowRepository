package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Booking;
import com.main.entity.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Long>{

	
	public List<Booking> findByUserId(Long userid);

	public List<Booking> findByShowId(Long showid);
	
	public List<Booking> findByBookingStatus(BookingStatus bookingStatus);
}

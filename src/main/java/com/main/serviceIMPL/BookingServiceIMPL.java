package com.main.serviceIMPL;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.BookingDto;
import com.main.entity.Booking;
import com.main.entity.BookingStatus;
import com.main.entity.Show;
import com.main.entity.User;
import com.main.repository.BookingRepository;
import com.main.repository.ShowRepository;
import com.main.repository.UserRepository;
import com.main.service.BookingService;

@Service
public class BookingServiceIMPL implements BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	ShowRepository showRepository;

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Booking createBooking(BookingDto bookingDto) {

		Show show = showRepository.findById(bookingDto.getShowId())
				.orElseThrow(() -> new RuntimeException("show not foud"));

		if (!isSeatsAvialable(show.getId(), bookingDto.getNumberOfSeats())) {
			throw new RuntimeException("Not enough seat are avilabel");
		}
		if (bookingDto.getNumberOfSeats().SIZE != bookingDto.getNumberOfSeats()) {
			throw new RuntimeException("seat number and number of Seats must be equals");
		}
		
		validateDuplicateSeats(show.getId(), bookingDto.getSeatNumber());
		
		User user= userRepository.findById(bookingDto.getUserId())
				                 .orElseThrow(()-> new RuntimeException("user not found"));
		
		Booking booking = new Booking();
		booking.setUsers(user);
		booking.setShow(show);
		booking.setNumberOfSeats(bookingDto.getNumberOfSeats());
		booking.setSeatNumber(bookingDto.getSeatNumber());
		booking.setPrice(calculateTotalAmount(show.getPrice(),bookingDto.getNumberOfSeats()));
		booking.setBookingTime(LocalDateTime.now());
		booking.setBookingStatus(BookingStatus.Pending);
		
		return bookingRepository.save(booking);
	}
	
	

	public boolean isSeatsAvialable(Long showid, Integer numberOfSeats) {

		Show show = showRepository.findById(showid).orElseThrow(() -> new RuntimeException("show not found"));

		int bookedSeats = show.getBooking().stream()
				.filter(booking -> booking.getBookingStatus() != BookingStatus.Canceled)
				.mapToInt(Booking::getNumberOfSeats).sum();

		return (show.getTheater().getTheaterCapacity() - bookedSeats) >= numberOfSeats;
	}

	public void validateDuplicateSeats(Long showId, List<String> seatNumbers) {
			
			Show show =showRepository.findById(showId)
	                 .orElseThrow(()-> new RuntimeException("show not found"));
			
			Set<String> occupiedSeats = show.getBooking().stream()
					                        .filter(b -> b.getBookingStatus() != BookingStatus.Canceled)
					                        .flatMap(b -> b.getSeatNumber().stream())
					                        .collect(Collectors.toSet());
		
			List<String> duplicateSeats = seatNumbers.stream()
					                                 .filter(occupiedSeats :: contains)
					                                 .collect(Collectors.toList());
			if(! duplicateSeats.isEmpty()) {
				throw new RuntimeException("Seats are already booked");
			}
			
		}

	public Integer calculateTotalAmount(Integer price, Integer numberOfSeats) {
		
		return price * numberOfSeats;
	}
	
	@Override
	public List<Booking> getUserBookings(Long userid) {

		   
		
		return bookingRepository.findByUserId(userid);
	}

	@Override
	public List<Booking> getShowBookings(Long showid) {
		
		return bookingRepository.findByShowId(showid);
	}

	@Override
	public Booking confirmBooking(Long id) {
	 
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Booking not found"));
				
		  if(booking.getBookingStatus() != BookingStatus.Pending) {
			  throw new RuntimeException("booking is not pending status");
		  }
		
		//Booking status is confirm ask the payment  
		  booking.setBookingStatus(BookingStatus.Confirmed);
		return bookingRepository.save(booking);
	}

	@Override
	public Booking cancelBooking(Long id) {
		
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Not Booking Found"));
		
		validateCancellation(booking);	 
		
		booking.setBookingStatus(BookingStatus.Canceled);	
		return bookingRepository.save(booking);
	}

	public void validateCancellation(Booking booking) {
		
		LocalDateTime showTime =booking.getShow().getShowTime();
		LocalDateTime deadLineTime = showTime.minusHours(2);
		
		if(LocalDateTime.now().isAfter(deadLineTime)) {
			 
			throw new RuntimeException("Cannot cancel the booking");
		}
		
		if(booking.getBookingStatus() == BookingStatus.Canceled) {
			throw new RuntimeException("booking is already canceled");
		}
	}
	
	
	@Override
	public List<Booking> getBookingByStatus(BookingStatus bookingStatus) {
		
		return bookingRepository.findByBookingStatus(bookingStatus);
	}

}

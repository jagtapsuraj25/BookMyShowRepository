package com.main.serviceIMPL;

import java.security.PublicKey;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.BookingDto;
import com.main.entity.Booking;
import com.main.entity.BookingStatus;
import com.main.entity.Show;
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

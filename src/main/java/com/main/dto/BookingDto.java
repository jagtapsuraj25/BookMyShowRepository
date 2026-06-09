package com.main.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.main.entity.BookingStatus;

public class BookingDto {
	
	private Integer  numberOfSerats;
	private LocalDateTime bookingTime;
	private Double price;
	private BookingStatus  bookingStatus;
	private List<String> seatNumber;
	private Long userId;
	private Long showId;
}

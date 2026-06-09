package com.main.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Booking {

	@Id

	private Long id;
	private Integer  numberOfSerats;
	private LocalDateTime bookingTime;
	private Double price;
	private BookingStatus  bookingStatus;
	
	private List<String> seatNumber;
	
}

package com.main.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Booking {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer  numberOfSerats;
	private LocalDateTime bookingTime;
	private Double price;
	private BookingStatus  bookingStatus;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="booking_seat-numbers")
	private List<String> seatNumber;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id",nullable = false)
	private User users;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "show_id",nullable =false)
	private Show show;
	
}

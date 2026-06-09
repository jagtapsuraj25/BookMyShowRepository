package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}

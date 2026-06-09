package com.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Long>{
	
	
	Optional<List<Theater>>findbyLocation(String theaterlocation);

}

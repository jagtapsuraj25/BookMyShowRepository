package com.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.dto.TheaterDto;
import com.main.entity.Theater;

public interface TheaterService {
	
	public Theater addTheater(TheaterDto theaterDto);

	public List<Theater> getTheaterByLocation(String theaterlocation);
	
	public Theater updateTheater(Long id,TheaterDto theaterDto);
		
	public void deleteTheater(Long id);
	
}

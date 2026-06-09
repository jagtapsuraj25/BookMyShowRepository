package com.main.serviceIMPL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.TheaterDto;
import com.main.entity.Theater;
import com.main.repository.TheaterRepository;
import com.main.service.TheaterService;

@Service
public class TheaterServiceIMPL implements TheaterService{

	@Autowired
	TheaterRepository theaterRepository;
	
	
	@Override
	public Theater addTheater(TheaterDto theaterDto) {
		
		Theater theater= new Theater();
		
		theater.setTheatername(theaterDto.getTheatername());
		theater.setTheaterlocation(theaterDto.getTheaterlocation());
		theater.setTheaterCapacity(theaterDto.getTheaterCapacity());
		theater.setScreenType(theaterDto.getScreenType());
		
		return theaterRepository.save(theater);
	}

	@Override
	public List<Theater> getTheaterByLocation(String theaterlocation) {
		
		Optional<List<Theater>> listOfTheaterBox = theaterRepository.findbyLocation(theaterlocation);
		
		if (listOfTheaterBox.isPresent()) {
			
			return listOfTheaterBox.get();
		}
		else throw new RuntimeException("No theaters Found for the location Enterd"+theaterlocation); 
	}

	@Override
	public Theater updateTheater(Long id, TheaterDto theaterDto) {
		
		 Theater theater =theaterRepository.findById(id)
				 .orElseThrow(()-> new RuntimeException("no theater found for the id"+id));
		         
		    theater.setTheatername(theaterDto.getTheatername());
			theater.setTheaterlocation(theaterDto.getTheaterlocation());
			theater.setTheaterCapacity(theaterDto.getTheaterCapacity());
			theater.setScreenType(theaterDto.getScreenType());
		 
			return theaterRepository.save(theater);
	}

	@Override
	public void deleteTheater(Long id) {
		theaterRepository.deleteById(id);
		
	}

	

}

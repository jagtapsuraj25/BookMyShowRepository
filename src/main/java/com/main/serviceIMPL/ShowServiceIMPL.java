package com.main.serviceIMPL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.ShowDto;
import com.main.entity.Booking;
import com.main.entity.Movie;
import com.main.entity.Show;
import com.main.entity.Theater;
import com.main.repository.MovieRepository;
import com.main.repository.ShowRepository;
import com.main.repository.TheaterRepository;
import com.main.service.ShowService;

@Service
public class ShowServiceIMPL implements ShowService {

	@Autowired
	ShowRepository showRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	TheaterRepository theaterRepository;

	@Override
	public Show createShow(ShowDto showDto) {

		Movie movie = movieRepository.findById(showDto.getMovieId())
				.orElseThrow(() -> new RuntimeException("No movie Found" + showDto.getMovieId()));

		Theater theater = theaterRepository.findById(showDto.getTheaterId())
				.orElseThrow(() -> new RuntimeException("No Theater Found" + showDto.getTheaterId()));

		Show show = new Show();

		show.setShowTime(showDto.getShowTime());
		show.setPrice(showDto.getPrice());
		show.setMovie(movie);
		show.setTheater(theater);

		return showRepository.save(show);

	}

	@Override
	public List<Show> getAllShow() {
		
		return showRepository.findAll();
	}

	@Override
	public List<Show> getShowsByMovie(Long id) {
	
		Optional<List<Show>> showListBoX = showRepository.findByMovieId(id);
		  if (showListBoX.isPresent()) {
	        	return showListBoX.get();
		}
		  else throw new RuntimeException("No show is avilabe");
	}

	@Override
	public List<Show> getShowsByTheater(Long id) {
	 
		List<Show> showListBox = showRepository.findByTheater(id).orElseThrow();
		return showListBox;
	}

	@Override
	public Show updateShow(Long id, ShowDto showDto) {
		Show show= showRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("No Show avilabe For Id"+id));
		

		Movie movie = movieRepository.findById(showDto.getMovieId())
				.orElseThrow(() -> new RuntimeException("No movie Found" + showDto.getMovieId()));

		Theater theater = theaterRepository.findById(showDto.getTheaterId())
				.orElseThrow(() -> new RuntimeException("No Theater Found" + showDto.getTheaterId()));
        
		show.setShowTime(showDto.getShowTime());
		show.setPrice(showDto.getPrice());
		show.setMovie(movie);
		show.setTheater(theater);

		return showRepository.save(show);

	}

	@Override
	public void deleteShow(Long id) {
	 
		if(!showRepository.existsById(id)) {
			throw new RuntimeException("no show available for the id"+id);
		}
		
		List<Booking> bookings =showRepository.findById(id).get().getBooking();
		if(bookings.isEmpty()) {
			throw new RuntimeException("Cant delete show with the exicting booking");		
		}
		showRepository.deleteById(id);
	}

} 

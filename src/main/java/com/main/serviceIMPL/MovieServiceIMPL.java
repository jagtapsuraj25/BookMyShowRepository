package com.main.serviceIMPL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.main.controller.MovieController;
import com.main.dto.MovieDto;
import com.main.entity.Movie;
import com.main.repository.MovieRepository;
import com.main.service.MovieService;

@Service
public class MovieServiceIMPL implements MovieService {

	private final MovieController movieController;

	@Autowired
	MovieRepository movieRepository;

	MovieServiceIMPL(MovieController movieController) {
		this.movieController = movieController;
	}

	@Override
	public Movie addMovie(MovieDto movieDto) {

		Movie movie = new Movie();
		movie.setName(movieDto.getName());
		movie.setDescription(movieDto.getDescription());
		movie.setGenre(movieDto.getGenre());
		movie.setReleaseDate(movieDto.getReleaseDate());
		movie.setDuration(movieDto.getDuration());
		movie.setLanuage(movieDto.getLanuage());

		return movieRepository.save(movie);
	}

	@Override
	public List<Movie> getAllMovies() {

		return movieRepository.findAll();
	}

	@Override
	public List<Movie> getMovieByGenre(String genre) {

		Optional<List<Movie>> listOfMovieBox = movieRepository.findByGenre(genre);

		if (listOfMovieBox.isPresent()) {
			return listOfMovieBox.get();
		} else
			throw new RuntimeException("No Movie Found For genre" + genre);
	}

	@Override
	public List<Movie> getMovieByLanguage(String lanuage) {

		Optional<List<Movie>> listOfMovieLanguage= movieRepository.findByLanguage(lanuage);
		
		if(listOfMovieLanguage.isPresent()) {
			
			return listOfMovieLanguage.get();
		}else {
		  
			 throw new RuntimeException("No movies found Language"+lanuage);
		}
		
	}

	@Override
	public Movie getMovieByTitle(String name) {
		  
		Optional<Movie> movieBox= movieRepository.findByName(name);
		
		if(movieBox.isPresent()){
			
			return movieBox.get();
		}else {
			
		throw new RuntimeException("No movie found for the "+name);
		}
	}

	@Override
	public Movie updateMovie(Long id, MovieDto movieDto) {
		
		Movie movie = movieRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("No movie found for the id"+id));
		
		movie.setName(movieDto.getName());
		movie.setDescription(movieDto.getDescription());
		movie.setGenre(movieDto.getGenre());
		movie.setReleaseDate(movieDto.getReleaseDate());
		movie.setDuration(movieDto.getDuration());
		movie.setLanuage(movieDto.getLanuage());
				
		return movieRepository.save(movie);
	}

	@Override
	public void deleteMovie(Long id) {
		
		movieRepository.deleteById(id);
	}

}

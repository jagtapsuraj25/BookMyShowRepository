package com.main.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.dto.MovieDto;
import com.main.entity.Movie;

public interface MovieService {

	 public Movie addMovie(MovieDto movieDto);
	 
	 public List<Movie> getAllMovies();
	 
	 public List<Movie> getMovieByGenre(String genre);
	 
	 public List<Movie> getMovieByLanguage(String lanuage);
	 
	 public Movie getMovieByTitle(String name);
	 
	 public Movie updateMovie(Long id, MovieDto movieDto);
	 
	 public void deleteMovie(Long id);

}

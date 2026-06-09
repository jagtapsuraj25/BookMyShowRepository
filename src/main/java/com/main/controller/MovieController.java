package com.main.controller;

import java.util.List;
import com.main.serviceIMPL.MovieServiceIMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.MovieDto;
import com.main.entity.Movie;
import com.main.service.MovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieServiceIMPL movieServiceIMPL;

	@Autowired
	MovieService movieService;

    MovieController(MovieServiceIMPL movieServiceIMPL) {
        this.movieServiceIMPL = movieServiceIMPL;
    }
	
   
	@PostMapping("/addmovie")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Movie> addMovie(@RequestBody MovieDto movieDto)
	{
		
		return ResponseEntity.ok(movieService.addMovie(movieDto));
	}
	
	@GetMapping("/getAllMovies")
	public ResponseEntity<List<Movie>> getAllMovies(){
				
	     return ResponseEntity.ok(movieService.getAllMovies());
	}
	
	@GetMapping("/getMovieByGenre")
	public ResponseEntity<List<Movie>> getMovieByGenre(@RequestParam String genre){
	
		
		return ResponseEntity.ok(movieService.getMovieByGenre(genre));
	}
	
	@GetMapping("/getMovieByLanguage")
	public ResponseEntity<List<Movie>> getMovieByLanguage(@RequestParam String lanuage){
	
		return ResponseEntity.ok(movieService.getMovieByLanguage(lanuage));
	}
	
	@GetMapping("/getMovieByName")
	public ResponseEntity<Movie>getMovieByTitle(@RequestParam String name){
		
		return ResponseEntity.ok(movieServiceIMPL.getMovieByTitle(name));
	}
	
	
	@PutMapping("/updateMovie/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Movie> updateMovie(@PathVariable long id,@RequestBody MovieDto movieDto){
		
		return ResponseEntity.ok(movieService.updateMovie(id,movieDto));
	}
	
	@DeleteMapping("deleteMovie/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteMovie(@PathVariable long id) {
	
		movieService.deleteMovie(id);
		
		return ResponseEntity.ok().build();
	}
	
	
}

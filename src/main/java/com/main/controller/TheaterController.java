package com.main.controller;

import java.util.List;

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
import com.main.BookMyShowApplication;
import com.main.dto.TheaterDto;
import com.main.entity.Theater;
import com.main.service.TheaterService;

@RestController
@RequestMapping("api/theater")
public class TheaterController {

    private final BookMyShowApplication bookMyShowApplication;

	@Autowired
	TheaterService theaterService;

    TheaterController(BookMyShowApplication bookMyShowApplication) {
        this.bookMyShowApplication = bookMyShowApplication;
    }
	
	@PostMapping("/addTheater")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Theater> addTheater(@RequestBody TheaterDto theaterDto){
		
		return ResponseEntity.ok(theaterService.addTheater(theaterDto));
	}
	
	@GetMapping("/getTheaterByLocation")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Theater>> getTheaterByLocation(@RequestParam String theaterlocation){
		
		return ResponseEntity.ok(theaterService.getTheaterByLocation(theaterlocation));
	}
	
	@PutMapping("/updateTheater")
	public ResponseEntity<Theater> updateTheater(@PathVariable Long id,@RequestBody TheaterDto theaterDto){
		
		return ResponseEntity.ok(theaterService.updateTheater(id,theaterDto));
		
	}
	
	@DeleteMapping("/deleteTheater")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity  deleteTheater(@PathVariable Long id){
		
		theaterService.deleteTheater(id);
		
		 return ResponseEntity.ok().build();
		
	}
	
	
}

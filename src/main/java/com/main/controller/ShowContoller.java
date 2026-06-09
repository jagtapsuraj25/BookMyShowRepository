package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.ShowDto;
import com.main.entity.Show;
import com.main.entity.Theater;
import com.main.service.ShowService;

@RestController
@RequestMapping("/api/show")
public class ShowContoller {
	
	@Autowired
	ShowService showService;
	
	@PostMapping("/")
	public ResponseEntity<Show> createShow(@RequestBody ShowDto showDto){
		
		return ResponseEntity.ok(showService.createShow(showDto));
	}

	@GetMapping("/getAllShow")
	public ResponseEntity<List<Show>>getAllShow(){
		
		return ResponseEntity.ok(showService.getAllShow());
	}
	
	@GetMapping("/getShowsByMovie")
	public ResponseEntity<List<Show>> getShowsByMovie(Long id)
	{
		return ResponseEntity.ok(showService.getShowsByMovie(id));
	}
	
	@GetMapping("/getShowsByTheater")
	public ResponseEntity<List<Show>> getShowsByTheater(@RequestParam Long  theaterid){	
		
		return ResponseEntity.ok(showService.getShowsByTheater(theaterid));
	}
	
	
	@PutMapping("/updateShow/{id}")
	public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody ShowDto showDto){
		
		return ResponseEntity.ok(showService.updateShow(id,showDto));
	}
	
	@DeleteMapping("/deleteShow/{id}")
	public ResponseEntity<Void> deleteShow(@PathVariable Long id){
		
		showService.deleteShow(id);
		
		return ResponseEntity.ok().build();
	}
	
}

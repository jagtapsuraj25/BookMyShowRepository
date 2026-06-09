package com.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.dto.ShowDto;
import com.main.entity.Show;
import com.main.entity.Theater;

public interface ShowService {
	
	public Show createShow(ShowDto showDto);
	
	public List<Show>getAllShow();
	
	public List<Show> getShowsByMovie(Long id);
	
	public List<Show> getShowsByTheater(Long theaterid);
	
	public Show updateShow(Long id,ShowDto showDto);
	
	public void deleteShow(Long id);
}

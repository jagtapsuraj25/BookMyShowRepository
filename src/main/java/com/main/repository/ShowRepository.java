package com.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Long>{

	Optional<List<Show>> findByMovieId(Long id);
	
	Optional<List<Show>> findByTheater(Long id);
	
	
}

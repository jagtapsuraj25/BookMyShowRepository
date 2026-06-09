package com.main.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class MovieDto {

	private String name;
	private String description;
	private String genre;
	private Integer duration;
	private LocalDate releaseDate;
	private String lanuage;
	
}

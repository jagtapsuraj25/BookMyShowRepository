package com.main.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ShowDto {

	private LocalDateTime showTime;
	private Integer price;
	private Long movieId;
	private Long theaterId;
}

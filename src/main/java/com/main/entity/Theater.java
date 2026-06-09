package com.main.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Theater {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String theatername;
	private String theaterlocation;
	private Integer theaterCapacity;
	private String screenType;  
	
	@OneToMany(mappedBy = "theater",fetch = FetchType.LAZY)
	private List<Show> show;

}

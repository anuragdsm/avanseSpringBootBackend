package com.avanse.springboot.DTO;

import lombok.Data;

@Data
public class CourseDTO {

	private long id;

	private String title;
	private String duration;

	private String writeup;
	
	private String description;


	private double fees;

	private String exams;
	private String documentsRequired;
}

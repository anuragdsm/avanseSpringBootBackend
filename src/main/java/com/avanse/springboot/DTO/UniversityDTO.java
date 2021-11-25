package com.avanse.springboot.DTO;


import java.util.Date;

import com.avanse.springboot.model.Course;

import lombok.Data;

@Data
public class UniversityDTO {

	private Long id;
	private String name;
	private String location;
	private String imageName;
	private String description;
	private String establishedYear;
	private String accomodation;
	private String intakePeriod;
	private String applicationProcess;

	private Date dateOfCreation;

	private Course course;
}

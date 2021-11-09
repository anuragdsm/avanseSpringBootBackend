package com.avanse.springboot.DTO;

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
	private String universitySlug;
}

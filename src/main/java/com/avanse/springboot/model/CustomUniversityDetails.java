package com.avanse.springboot.model;

import com.avanse.springboot.DTO.UniversityDTO;

// Creating a custom university details class so that we can pass the university dto object and call a function
// to return the image name.

public class CustomUniversityDetails extends UniversityDTO{

	public CustomUniversityDetails(UniversityDTO universityDTO) {
		super();
	}
	
	public String getImageName() {
		return super.getImageName();
	}
	
}

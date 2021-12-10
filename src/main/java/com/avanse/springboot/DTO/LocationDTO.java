package com.avanse.springboot.DTO;

import lombok.Data;

@Data
public class LocationDTO {

	private Long id;
	private String city;
	/*
	 *  This is the icon that will be uploaded by the user
	*/
	private String iconImageName; 
}

package com.avanse.springboot.DTO.forms.applyNow;

import java.util.Date;

import lombok.Data;

@Data
public class ApplyNowGeneralDTO {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String emailId;
	private String city;
	private String placeOfStudy;
	private String loanAmount;
	private String admissionStatus;
	private String permission;
	private String timeOfStudy;

//	Date has to be implemented later
//	private Date timeOfStudy;
}

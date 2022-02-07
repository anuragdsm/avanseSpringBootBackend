package com.avanse.springboot.DTO.forms.applyNow;

import lombok.Data;

@Data
public class ApplyNowGeneralDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String emailId;
	private String city;
	private String placeOfStudy;
	private String loanAmount;
	private String AdmissionStatus;

}
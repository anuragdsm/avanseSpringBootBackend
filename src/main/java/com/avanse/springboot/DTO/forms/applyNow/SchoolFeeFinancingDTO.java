package com.avanse.springboot.DTO.forms.applyNow;

import lombok.Data;

@Data
public class SchoolFeeFinancingDTO {

	private Long id;
	private String firstName;
	private String phoneNumber;
	private String email;
	private String city;

	private Long loanAmount;
	private String nameOfTheSchool;
}
//githu
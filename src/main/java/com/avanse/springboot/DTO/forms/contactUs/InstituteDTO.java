package com.avanse.springboot.DTO.forms.contactUs;

import javax.persistence.Basic;
import javax.persistence.Lob;

import lombok.Data;

@Data
public class InstituteDTO {
	
	private Long id;
	private String nameOfPerson;
	private String nameOfInstitute;
	private String phoneNumber;
	private String email;
	private String city;
	private String subjectLine;
	private String loanType;

}

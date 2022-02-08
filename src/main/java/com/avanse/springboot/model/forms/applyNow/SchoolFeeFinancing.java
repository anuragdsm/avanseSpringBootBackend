package com.avanse.springboot.model.forms.applyNow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "SCHOOL_FEE_FINANCING_LEADS")
public class SchoolFeeFinancing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String phoneNumber;
	private String email;
	private String city;

	private Long loanAmount;
	private String nameOfTheSchool;
	
	

}

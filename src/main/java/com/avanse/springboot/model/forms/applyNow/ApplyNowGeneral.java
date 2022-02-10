package com.avanse.springboot.model.forms.applyNow;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// This is the form that is apply-now html which will be generic in nature
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table ( name =  "APPLY_NOW_GERNERAL")
public class ApplyNowGeneral {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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

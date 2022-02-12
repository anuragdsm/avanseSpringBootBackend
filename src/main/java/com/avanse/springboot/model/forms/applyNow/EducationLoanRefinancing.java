package com.avanse.springboot.model.forms.applyNow;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//For student-loan-refinancing.html

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EDUCATION_LOAN_REFINANCING_LEADS")
public class EducationLoanRefinancing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phoneNumber;
	private String location;
	private Long balanceLoanAmount;	
	private String universityAttending;
	private String studyingPlace;
	private String studyingCourse;
	private String graduateMonthAndYear;
	private Integer interestRate;
	
	@CreationTimestamp
	private Date dateOfCreation;
	
}

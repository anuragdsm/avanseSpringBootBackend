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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EDUCATION_INSTITUTION_LOAN")
public class EducationInstitutionLoan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String city;
	private String loanType;
	private String loanAmount;
	private String loanUsage;
	private String institutionType;
	private String CurriculumBoard;
	private String vintage;
	private String studentStrength;
	
	@CreationTimestamp
	private Date dateOfCreation;
	
}

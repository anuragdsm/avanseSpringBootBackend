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
@Table(name = "EXECUTIVE_EDUCATION_LOAN_LEADS")
public class ExecutiveEducationLoan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String courseName;
	private String loanAmount;
	

}

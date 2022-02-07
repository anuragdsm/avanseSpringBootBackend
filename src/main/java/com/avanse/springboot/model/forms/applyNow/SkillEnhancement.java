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
@Table(name = "SKILL_ENHACEMENT_DATA")
public class SkillEnhancement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String LastName;
	private String phoneNumber;
	private String email;
	private String city;
	private String courseName;
	private String courseProvider;
	private String loanAmount;
	

}

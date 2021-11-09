package com.avanse.springboot.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;



// University model class to create data and manage the data fields
// Using lombok; so getters and setters will be generated automatically

@Data
@Entity
@Table(name="universities")
//@AllArgsConstructor
public class University {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="university_id")  	//In the university table the id will saved as university_id
	private Long id;
	
	private String name;
	private String location;
	private String imageName;
	private String description;
	private String establishedYear;
	private String accomodation;
	private String intakePeriod;
	private String applicationProcess;
	private String universitySlug;
}

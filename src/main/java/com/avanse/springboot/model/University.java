package com.avanse.springboot.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



// University model class to create data and manage the data fields
// Using lombok; so getters and setters will be generated automatically

@NoArgsConstructor
@Data
@Entity
@Table(name="universities")
//@AllArgsConstructor

public class University implements Serializable {
	

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@CreationTimestamp
	private Date dateOfCreation;
	
	@ToString.Exclude
	@OneToOne(targetEntity = Course.class ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy ="university") 
	private Course course;

	/*
	 * Default constructor is created using lombok
	 * Constructor using field created ommiting the id, course object and creation timestamp
	*/
	public University(Course course, String name, String location, String imageName, String description, String establishedYear,
			String accomodation, String intakePeriod, String applicationProcess, String universitySlug) {
		super();
		this.name = name;
		this.location = location;
		this.imageName = imageName;
		this.description = description;
		this.establishedYear = establishedYear;
		this.accomodation = accomodation;
		this.intakePeriod = intakePeriod;
		this.applicationProcess = applicationProcess;
		this.universitySlug = universitySlug;
		this.course=course;
	}
	
}

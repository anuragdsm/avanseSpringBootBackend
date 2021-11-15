package com.avanse.springboot.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



// University model class to create data and manage the data fields
// Using lombok; so getters and setters will be generated automatically

@NoArgsConstructor
@Data
@Entity
@Table(name="universities")
@AllArgsConstructor

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
	
	private Boolean isUniversityActive = true;
	
	@CreationTimestamp
	private Date dateOfCreation;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "university", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch = FetchType.EAGER)
//	@JoinTable(name="university_course", 
//				joinColumns = {@JoinColumn(name="UNIVERSITY_ID", referencedColumnName = "UNIVERSITY_ID")},
//				inverseJoinColumns = {@JoinColumn(name="COURSE_ID", referencedColumnName = "COURSE_ID")})
	private List<Course> courses;
	
	
	/*Function to create a bidirectional link between the two tables.
	 * We will have to use the this keyword to pass it as the argument.
	 * 
	*/
	public void addTheCourse(Course tempCourse) {
		if(courses == null) {
		
			courses = new ArrayList<Course>();
		}
		
		courses.add(tempCourse);
		tempCourse.setUniversity(this);
		
	}
	
	

	public University(University university) {
//		super();
		this.name = university.getName();
		this.location = university.getLocation();
		this.imageName = university.getImageName();
		this.description = university.getDescription();
		this.establishedYear = university.getEstablishedYear();
		this.accomodation = university.getAccomodation();
		this.intakePeriod = university.getIntakePeriod();
		this.applicationProcess = university.getApplicationProcess();
		this.universitySlug = university.getUniversitySlug();
		this.isUniversityActive = university.getIsUniversityActive();
		this.dateOfCreation = university.getDateOfCreation();
		this.courses = university.getCourses(); 
	}

	/*
	 * Default constructor is created using lombok
	 * Constructor using field created ommiting the id, course object and creation timestamp
	*/
		
}

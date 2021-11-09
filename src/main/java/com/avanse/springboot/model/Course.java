package com.avanse.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@Column(name="course_id")
	private long id;
	
	private String title;
	private String duration;
	private String description;
	
	private String writeup;
	
	private double fees;
	
	private String exams;
	private String documentsRequired;
	
}

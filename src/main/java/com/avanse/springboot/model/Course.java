package com.avanse.springboot.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private long id;

	@CreationTimestamp
	private Date dateOfCreation;

	private String title;
	private String duration;
	private String description;

	private String writeup;

	private double fees;

	private String exams;
	private String documentsRequired;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "UNIVERSITY_ID", referencedColumnName = "UNIVERSITY_ID")
//	

	@ToString.Exclude
	@OneToOne(targetEntity = University.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "university_id", referencedColumnName = "university_id")

	
	private University university;

	/*
	 * Constructor ommiting the ID, creation timestamp and university Object
	 * Default constructor is created using lombok
	*/
	public Course(University university, String title, String duration, String description, String writeup, double fees,
			String exams, String documentsRequired) {
		super();
		this.title = title;
		this.duration = duration;
		this.description = description;
		this.writeup = writeup;
		this.fees = fees;
		this.exams = exams;
		this.documentsRequired = documentsRequired;
		this.university=university;
	}

}

package com.avanse.springboot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "courses")
public class Course implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private Long id;

	@CreationTimestamp
	private Date dateOfCreation;

	private String title;
	private String duration;
	
	@Lob
	@Basic
	private String description;

	private Double fees;

	private String exams;
	
	private String documentsRequired;

	@ManyToOne
	@JoinColumn(name = "UNIVERSITY_ID")
	private University university;


	/*
	 * Constructor ommiting the ID, creation timestamp and university Object
	 * Default constructor is created using lombok
	*/
	
}

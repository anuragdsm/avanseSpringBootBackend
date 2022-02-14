package com.avanse.springboot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String examsEligibility;
	private Boolean isCourseActive = true;
	
	
	private String documentsRequired;
	private String academicDocumentsRequired;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "UNIVERSITY_ID")
	private University university;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<TypeDetailsCourse> types; // to be made as multiple checkbox
	
	@Lob
	@Basic
	private String staticContent;


	/*
	 * Constructor ommiting the ID, creation timestamp and university Object
	 * Default constructor is created using lombok
	*/
	
}

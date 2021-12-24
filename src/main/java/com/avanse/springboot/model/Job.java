package com.avanse.springboot.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="Jobs")
public class Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3267254915405226279L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	
	@CreationTimestamp
	private Date postDate; 
	private String description;
	private String rolesAndResponsibilities;
	private String educationQualification;
	private String skills;
	private Integer experience;
	private Integer Compensation;
	private String postedBy;
	private String location;
	
	public Job(String title, String description, String rolesAndResponsibilities, String postedBy, String location) {
		super();
		this.title = title;
		this.description = description;
		this.rolesAndResponsibilities = rolesAndResponsibilities;
		this.postedBy = postedBy;
		this.location = location;
	}
}

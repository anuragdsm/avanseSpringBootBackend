package com.avanse.springboot.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="jobs")
public class Job implements Serializable {

	private static final long serialVersionUID = 3267254915405226279L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	
	@CreationTimestamp
	private Date postDate; 
	
	private String jobCreatedDate;
	
	private String shortDescription;
	
	private String skills;
	
	private Boolean isJobActive=true;
	
	private String experienceInYears;
	
	@Lob
	@Basic
	private String description;
	private String postedBy;
	@ManyToMany(mappedBy = "jobs", fetch = FetchType.LAZY )
	private List<Location> locationList;
		
}

package com.avanse.springboot.DTO;

import java.util.Date;
import lombok.Data;

@Data
public class JobDTO {

	private Long id;
	private String title;
	private Date postDate; 
	private String description;
	private String rolesAndResponsibilities;
	private String educationQualification;
	private String skills;
	private Integer experience;
	private Integer Compensation;
	private String postedBy;
	private String location;
	private String resume;
	
}

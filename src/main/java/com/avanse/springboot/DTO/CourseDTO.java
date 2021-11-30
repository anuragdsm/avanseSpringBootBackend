package com.avanse.springboot.DTO;
import java.util.Date;

import com.avanse.springboot.model.University;
import lombok.Data;

@Data
public class CourseDTO {

	private Long id;
	private String title;
	private String duration;
	private String description;
	private Double fees;
	private Date dateOfCreation;
	private String exams;
	private String documentsRequired;
	private University university;
	
}

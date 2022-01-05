package com.avanse.springboot.DTO;

import java.util.Date;
import lombok.Data;

@Data
public class JobDTO {

	private Long id;
	private String title;
	private Date postDate; 
	private String description;
	private String postedBy;
	private String location;
	private String jobCreatedDate;

	
}

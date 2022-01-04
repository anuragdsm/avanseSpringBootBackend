package com.avanse.springboot.model.forms.contactUs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEDIA_CONTACTUS_FORM" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String mediaHouse;
	private String phoneNumber;
	private String emailId;
	private String city;
	private String subjectLine;
	
}

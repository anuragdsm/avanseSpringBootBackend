package com.avanse.springboot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "leads")
@Getter
@Setter
@AllArgsConstructor
public class Lead {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	
	@CreationTimestamp
	private Date leadGenerationTime;

	/*
	*/
	
	private String lastName;
	private String email;	
	private String phoneNumber;
	private String country;
	private String city;
	
	private String customMessage;
	private String countryInterested; 
	private String universityInterested; 
	private String courseInterested;
	
	private Long loanAmount;
	private String admissionStatus;
	
	
//	I authorize Avanse Financial Services to connect with me over call and Whatsapp overriding my registration with NDNC

//	private Boolean userPermission; can be implemented later
	
	/*
	 * default constructor
	*/
	public Lead() {
		
	}
	
	/*
	 * Basic Lead capture information constructor
	*/

	public Lead(String firstName, String email, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	

}





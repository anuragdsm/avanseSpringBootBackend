package com.avanse.springboot.model.forms.contactUs;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "INSTITUTE_CONTACTUS_FORM" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Institute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nameOfPerson;
	private String nameOfInstitute;
	private String phoneNumber;
	private String email;
	private String city;
	@Lob
	@Basic
	private String subjectLine;
	private String loanType;
	
	

}

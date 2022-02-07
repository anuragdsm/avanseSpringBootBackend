package com.avanse.springboot.model.forms.applyNow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "PARTNER_WITH_US_LEADS")
public class PartnerWithUs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	private String nameOfFirm;
	private String partnerType;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private String city;
	

}

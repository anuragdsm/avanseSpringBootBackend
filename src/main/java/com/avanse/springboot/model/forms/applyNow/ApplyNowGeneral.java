package com.avanse.springboot.model.forms.applyNow;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// This is the form that is apply-now html which will be generic in nature
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table ( name =  "Student")
public class ApplyNowGeneral {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@Column(name ="mobile")
	private String contactNumber;
	@Column(name ="email")
	private String emailId;
	
	@Column(name ="cityresidence")
	private String city;
	
	@Column(name = "countrystudy")
	private String placeOfStudy;
	@Column(name = "timeofstudy")
	private String timeOfStudy;
	
	@Column(name = "loanamnt")
	private String loanAmount;
	
	@Column(name = "admsecured")
	private String admissionStatus;
	
	@Column(name = "authorise")
	private String permission;
	
//	Added More Fields
	
	private String source;
	private String type;
	private String campaignid;
	private String adgroup;
	private String keywords;
	private String ip_address;
	
	@CreationTimestamp
	private Date created; 
	
	private String othercountry;
	private String rmname;
	
	private String branch;
	
	@Lob
	@Basic
	private String crmstatus;
	private String status;
	
	@Lob
	@Basic
	private String description;
	
	@Lob
	@Basic
	private String gclid;
	
	private String leadid;
	
	
	
	@Lob
	@Basic
	private String crmrequest;
	private Date crmresponsetime;
	private String curlerror;
	private String othercity;
	private String university;
	private String otheruniversity;
	private String countrycode;
	private String citycode;
	private String universitycode;
	private String whatsappconsent;
	private String otp;
	
	private String mySrc;
	private Integer mail_sent;
	
	
//	Date has to be implemented later
	
//	private Date timeOfStudy;
	
	
}

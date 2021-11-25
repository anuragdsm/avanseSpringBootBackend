package com.avanse.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User implements Serializable {


	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotEmpty
	private String firstName;
		
	private String lastName;

	
	@Column(nullable=false, unique = true) //this can also be used as an alternate key...
	@NotEmpty
	@Email(message="{errors.invalid_email}")
	private String email;

	private String password;

//	temporarily using string for dates and will change later to date object...
//	Trying: dateTimeFormat annotation
//	**REASON: To speed up the build process
	
	
	private String dateOfBirth; 
	
	private String maritalStatus;
	private String gender;
	private String phoneNumber;
	private String  city;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
				joinColumns = {@JoinColumn(name="USER_ID", referencedColumnName = "ID")},
				inverseJoinColumns = {@JoinColumn(name="ROLE_ID", referencedColumnName = "ID")}	
				)
	
	private List<Role> roles;
	
	
	/*
	 * Default Constructor very much needed in order to log in
	 * Without this, hibernate doesn't allow to enter
	*/
	public User() {

	}

	public User(User user) {
//		super();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.dateOfBirth = user.getDateOfBirth();
		this.maritalStatus = user.getMaritalStatus();
		this.gender = user.getGender();
		this.phoneNumber = user.getPhoneNumber();
		this.city = user.getCity();
		this.roles = user.getRoles();
	}		
}

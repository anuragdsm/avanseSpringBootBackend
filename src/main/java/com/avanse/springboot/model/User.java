package com.avanse.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {

	public User(String firstName, String lastName, String email) {
		// TODO Auto-generated constructor stub
		super();
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	
}

/*
 * This location for now is going to be linked to the careers page
*/

package com.avanse.springboot.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="locations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7200482023017202972L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String city;
	
	public Location(String city) {
		super();
		this.city = city;
	}
	
	
}




/*
 * This location for now is going to be linked to the careers page
*/

package com.avanse.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="locations")
@Getter
@Setter
@NoArgsConstructor
public class Location implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7200482023017202972L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String city;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "Locations_Jobs", 
				joinColumns = {@JoinColumn(name="location_id")},
				inverseJoinColumns = {@JoinColumn(name = "job_id")}
	)
	private List<Job> jobs;
	
	public Location(String city) {
		super();
		this.city = city;
	}
	
	
}




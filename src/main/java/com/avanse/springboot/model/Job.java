package com.avanse.springboot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="Jobs")
public class Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3267254915405226279L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	
	@CreationTimestamp
	private Date postDate; 
	
	@Lob
	@Basic
	private String description;
	private String postedBy;
	@ManyToMany(mappedBy = "jobs", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
	private List<Location> locationList = new ArrayList<Location>();
		
}

package com.avanse.springboot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MEDIA_DATA")
@AllArgsConstructor
public class Media implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5751195269420144744L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String fileName;
	private String link;
	private String altTag;
	private int height;
	private int width;
	private Date uploadedOn;
	private Double size;
	public Media() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Media(String name) {
		super();
		this.name = name;
	}
	
	
	
}




package com.avanse.springboot.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Menu")
public class Menu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	private String name;	
	private String link;
	
	/*
	 * Create the constructor so that it can 
	 * take the name as well and the 
	 * link and only name also
	*/
	public Menu(String name, String link) {
		super();
		this.name = name;
		this.link = link;
	}
	
	public Menu(String name) {
		super();
		this.name = name;
	}
}



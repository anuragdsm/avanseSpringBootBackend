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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="CATEGORIES")
public class PostCategory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "category_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Post> postList;
	
	public PostCategory() {
	}
	
	
	public PostCategory(String name) {
		super();
		this.name = name;
	}
}



package com.avanse.springboot.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pages")
public class Page implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String pageTitle; 
	private String subTitle;
	
	/*
	 * The value of 1 will represent Full page
	 * The value of 2 will represent Box Container
	*/
	private Integer pageLayout; 
	
	@Lob
	@Basic
	private String mainSection;
	
	@Lob
	@Basic
	private String content1;
	
	@Lob
	@Basic
	private String content2;
	
	
	
	/*
	 * image variables
	*/
	private String bannerImageName;
	private String bannerImageAlt;
	
	private String headerTitle;
	
	/*
	 * Codes by the admin
	*/
	@Lob
	@Basic
	private String cssCode;
	
	@Lob
	@Basic
	private String jsCode;
	
	/*
	 * SEO variables
	*/
	
	private String metaTitle;
	private String metaKeyword;
	
	@Lob
	@Basic
	private String metaDescription;
	
	

	

}

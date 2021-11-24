package com.avanse.springboot.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "pages")
public class Page implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pageTitle;
	private String slug;
	private String subTitle;
	
	private String mainSection;
	private String content1;
	
	private String content2;
	
	private String bannerFileName;
	private String bannerImageAlt;
	
	private String headerTitle;
	
	private Boolean isMainPage;
	private String parentPage;
	
	/*
	 * The value of 1 will represent Full page
	 * The value of 2 will represent Box Container
	*/
	private Integer pageLayout; 
	
	
	
	/*
	 * SEO variables
	*/
	
	private String metaTitle;
	private String metaKeyword;
	private String metaDescription;
	
	/*
	 * Codes by the admin
	*/
	
	private String cssCode;
	private String jsCode;
	
	
	
	

}

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
//@AllArgsConstructor
@Table(name = "pages")
public class Page implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fileName;
	private String pageTitle;
	
	
	/*
	 * Banner variables
	*/
	private String bannerHeading; 
	private String bannerSubHeading;
	
	/*
	 * image variables
	*/
	private String bannerImageName;
	private String bannerImageAlt;
	
	
	/*
	 * The value of 1 will represent Full page
	 * The value of 2 will represent Box Container
	*/
	
//	private Integer pageLayout; 
	
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
	/*
	 * public Page() { // TODO Auto-generated constructor stub }
	 */

	public Page(String pageTitle, String bannerHeading, String bannerSubHeading, String bannerImageName,
			String bannerImageAlt, String mainSection, String content1, String content2, String cssCode, String jsCode,
			String metaTitle, String metaKeyword, String metaDescription) {
//		super();
		this.pageTitle = pageTitle;
		this.bannerHeading = bannerHeading;
		this.bannerSubHeading = bannerSubHeading;
		this.bannerImageName = bannerImageName;
		this.bannerImageAlt = bannerImageAlt;
		this.mainSection = mainSection;
		this.content1 = content1;
		this.content2 = content2;
		this.cssCode = cssCode;
		this.jsCode = jsCode;
		this.metaTitle = metaTitle;
		this.metaKeyword = metaKeyword;
		this.metaDescription = metaDescription;
	}
	
	
	
	


}

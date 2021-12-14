package com.avanse.springboot.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class PostDTO {

	private Long id;
	private Date dateOfCreation;
	private Date lastModified;
	private Boolean isPageActive;

	private String fileName;
	private String pageTitle;

	private String bannerHeading;
	private String bannerSubHeading;

	/*
	 * image variables
	 */
	private String bannerImageName;
	private String bannerImageAlt;

	/*
	 * The value of 1 will represent Full page The value of 2 will represent Box
	 * Container
	 */
//	private Integer pageLayout; 

	private String mainSection;
	/*
	 * Codes by the admin
	 */
	private String consolidatedHTMLCode;
	/*
	 * SEO variables
	 */
	private String metaTitle;
	private String metaKeyword;
	private String metaDescription;

}
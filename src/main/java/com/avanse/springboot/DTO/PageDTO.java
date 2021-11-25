package com.avanse.springboot.DTO;



import lombok.Data;

@Data
public class PageDTO {

	private Long id;
	
	private String pageTitle; 
	private String subTitle;
	
	/*
	 * The value of 1 will represent Full page
	 * The value of 2 will represent Box Container
	*/
	private Integer pageLayout; 
	private String mainSection;
	
		private String content1;
	
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
	private String cssCode;
	
	private String jsCode;
	
	/*
	 * SEO variables
	*/
	
	private String metaTitle;
	private String metaKeyword;
	
		private String metaDescription;

}

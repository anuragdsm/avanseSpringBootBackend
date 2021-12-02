package com.avanse.springboot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Entity
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "blogs")
public class Post implements Serializable {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@CreationTimestamp
		private Date dateOfCreation;
		
		private Date lastModified;
		
		private Boolean isBlogPublished=false;
		
		
		private String fileName;
		private String postTitle;
		
		
		/*
		 * Banner fileds including image
		*/
		private String bannerHeading; 
		private String bannerSubHeading;
		private String bannerImageName;
		private String bannerImageAlt;
		
		
		/*
		 * The value of 1 will represent Full page
		 * The value of 2 will represent Box Container
		*/
		
//		private Integer pageLayout; 
		
		@Lob
		@Basic
		private String mainSection;
		
		/*
		 * Codes by the admin
		*/
		@Lob
		@Basic
		private String cssCode;
		
		@Lob
		@Basic
		private String jsCode;
		
		@Lob
		@Basic
		private String consolidatedHTMLCode;
		
		/*
		 * SEO fields
		*/
		private String metaTitle;
		private String metaKeyword;
		
		@Lob
		@Basic
		private String metaDescription;

}

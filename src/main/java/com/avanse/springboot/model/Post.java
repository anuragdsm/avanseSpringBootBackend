package com.avanse.springboot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "posts")
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
		
		private Boolean isPostPublished=false;
		private String fileName;
		private String postTitle;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "categoryId", referencedColumnName = "category_id")
		private PostCategory postCategory;
		
		
		/*
		 * Banner fileds including image
		*/
		private String heading; 
		private String subHeading;
		private String featuredImageName;
		private String featuredImageAltText;
		
		/*
		 * The value of 1 will represent Full page
		 * The value of 2 will represent Box Container
		*/
			
		@Lob
		@Basic
		private String mainSection;	
		
		/*
		 * Codes by the admin
		*/
		
		@Lob
		@Basic
		private String consolidatedHTMLCode;
		/*
		 * SEO fields
		*/
		private String metaTitle;
		
		@Lob
		@Basic
		private String metaDescription;

}

package com.avanse.springboot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
		
		private String dateOfPostCreation;
		
//		private Date lastModified;
		
		@ManyToMany(mappedBy = "postList", fetch = FetchType.LAZY)
		private List<PostCategory>postCategoryList;
		
		private Boolean isPostActive=true;
		private String fileName;
		private String postTitle;
		
		
		
		private String extractedFileName;
		private String postLink;


		
//		@ManyToOne(fetch = FetchType.LAZY)
//		@JoinColumn(name = "categoryId", referencedColumnName = "category_id")
//		private PostCategory postCategory;
//		
//		
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

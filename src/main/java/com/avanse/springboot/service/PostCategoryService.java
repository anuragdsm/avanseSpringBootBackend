package com.avanse.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avanse.springboot.model.Course;
import com.avanse.springboot.model.Post;
import com.avanse.springboot.model.PostCategory;
import com.avanse.springboot.repository.CourseRepository;
import com.avanse.springboot.repository.PostCategoryRepository;

@Service
public class PostCategoryService {
	@Autowired
	PostCategoryRepository postCategoryRepository;	
//	Function for adding the category
	public void addPostCategory(PostCategory postCategory) {
		postCategoryRepository.save(postCategory);
	}
//	Function to display all the categories
	public List<PostCategory> getAllPostCategories(){
		return postCategoryRepository.findAll();
	}
//	Function to delete a category by ID
	public void removePostCategoryById(Long id) {
//		categoryRepository.deleteById(id);
		postCategoryRepository.deleteById(id);
	}
//	Function to update Category but before that we need to fetch it	
	public Optional<PostCategory> getPostCategoryById(Long id){
		return  postCategoryRepository.findById(id);
	}	
	/*
	 * public long numberOfCourses() { return courseRepository.count(); }
	 */	
	public long numberOfPostCategories() {
		return postCategoryRepository.count();
	}
}

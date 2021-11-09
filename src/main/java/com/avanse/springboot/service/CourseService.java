package com.avanse.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.Course;
import com.avanse.springboot.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;
	
//	Return the list of courses
	public List<Course> getAllCourses(){
		
		return courseRepository.findAll();
	}
	
//	Function to add a course
	public void addCourse(Course course) {
		
		courseRepository.save(course);
	}
	
	
//	Delete a course 
	public void deleteCourse(long id) {
		courseRepository.deleteById(id);
	}

//	Update a course
//	We need to use optional class because in order to update the content we have to fetch it first 
	
	public Optional<Course> getCourseById(long id){
		return courseRepository.findById(id);
	}
	
	
}

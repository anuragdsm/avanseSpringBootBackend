package com.avanse.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avanse.springboot.model.Course;
import com.avanse.springboot.repository.CourseRepository;

@Service
public class CourseService {
	
//	public static final int COURSES_PER_PAGE=4;

	@Autowired
	CourseRepository courseRepository;
	
//	Return the list of courses
	public List<Course> getAllCourses(){
		
		return courseRepository.findAll();
	}
	
	
//	public Page<Course> listByPage(int pageNum){
//		Pageable pageable = PageRequest.of(pageNum-1, COURSES_PER_PAGE);
//		return courseRepository.findAll(pageable);
//	}
//	
	
//	Function to add a course
	
	@Transactional
	public void addCourse(Course course) {

		courseRepository.save(course);
	}
	
	
//	Delete a course
	@Transactional
	public void deleteCourse(long id) {
		courseRepository.deleteById(id);
	}

//	Update a course
//	We need to use optional class because in order to update the content we have to fetch it first 
	
	public Optional<Course> getCourseById(long id){
		return courseRepository.findById(id);
	}
	
	/*
	 * Creating a service to get the list of all the courses according to
	 * the university id... 
	*/
	public List<Course> getUniversityByCourseId(long id){
	
		return courseRepository.findAllByUniversity_Id(id);
	}
	
	/*
	 * Returns the number of courses in the database
	*/
	public long numberOfCourses() {
		return courseRepository.count();
	}
	
	
}

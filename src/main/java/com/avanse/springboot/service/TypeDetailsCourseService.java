package com.avanse.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avanse.springboot.model.TypeDetailsCourse;
import com.avanse.springboot.repository.TypeDetailsCourseRepository;

@Service
public class TypeDetailsCourseService {
	
	@Autowired
	TypeDetailsCourseRepository typeDetailsCourseRepository;
	
	public List<TypeDetailsCourse> getAllTypeDetails(){
		return typeDetailsCourseRepository.findAll();
	}
	
	
//	Function to delete the university from the database
	@Transactional
	public void removeTypeDetailsCourseById(long id) {
		typeDetailsCourseRepository.deleteById(id); 
	}

//	Function to save the university in the database
	@Transactional
	public void addTypeDetailsCourse(TypeDetailsCourse typeDetailsCourse) {
		typeDetailsCourseRepository.save(typeDetailsCourse);
	}
	
//	Optional class to fetch the results on the same page...
	public Optional<TypeDetailsCourse> getTypeDetailsCourseById(long id){
		
		return typeDetailsCourseRepository.findById(id);
	}
	
	/*
	 * Returns the number of universities in the database
	*/
	public long numberOfTypeDetailsCourses() {
		return typeDetailsCourseRepository.count();
	}
	

	

}

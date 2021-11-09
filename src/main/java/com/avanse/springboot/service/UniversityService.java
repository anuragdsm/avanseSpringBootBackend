package com.avanse.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.University;
import com.avanse.springboot.repository.UniversityRepository;

@Service
public class UniversityService {

	@Autowired
	UniversityRepository universityRepository;
	
//	Return the list of all the universities
	public List<University> getAllUniversity(){
		return universityRepository.findAll();
	}

//	Function to delete the university from the database
	public void removeUniversityById(long id) {
		universityRepository.deleteById(id); 
	}

//	Function to save the university in the database
	public void addUniversity(University university) {
		universityRepository.save(university);
	}
	
//	Optional class to fetch the results on the same page...
	public Optional<University> getUniversityById(long id){
		
		return universityRepository.findById(id);
	}
	
	
	public University getUniversity(String id) {
		return getAllUniversity().stream().filter(u -> u.getId().equals(id)).findFirst().get();
	}
	
}

package com.avanse.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avanse.springboot.model.University;
import com.avanse.springboot.repository.UniversityRepository;

@Service
public class UniversityService {

	public static final int UNIVERSITTIES_PER_PAGE = 4;
	
	
	@Autowired
	UniversityRepository universityRepository;
	
//	Return the list of all the universities
	public List<University> getAllUniversity(){
		return universityRepository.findAll();
	}
	
	
	/*
	 * // Manual Pagination to be disable as we are going to use via template
	 */
//	public Page<University>listByPage(int pageNum){
//		Pageable pageable = PageRequest.of(pageNum -1, UNIVERSITTIES_PER_PAGE);		
//		return universityRepository.findAll(pageable);
//	}
//	public Page<University>listByPageInDescending(int pageNum){
//		Pageable pageable = PageRequest.of(pageNum -1, UNIVERSITTIES_PER_PAGE,Sort.by("dateOfCreation").descending());		
//		return universityRepository.findAll(pageable);
//	}
	
	

//	Function to delete the university from the database
	@Transactional
	public void removeUniversityById(long id) {
		universityRepository.deleteById(id); 
	}

//	Function to save the university in the database
	@Transactional
	public void addUniversity(University university) {
		universityRepository.save(university);
	}
	
//	Optional class to fetch the results on the same page...
	public Optional<University> getUniversityById(long id){
		
		return universityRepository.findById(id);
	}
	
	/*
	 * Returns the number of universities in the database
	*/
	public long numberOfUniversities() {
		return universityRepository.count();
	}
	
	
}

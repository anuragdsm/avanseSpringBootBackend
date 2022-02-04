package com.avanse.springboot.service.forms.contactUs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.forms.contactUs.Institute;
import com.avanse.springboot.repository.forms.contactUs.InstituteRepository;

@Service
public class InstituteService {
	
	public static final int INSTITUTES_PER_PAGE = 4;
	
	@Autowired
	InstituteRepository instituteRepository;
	
	public List<Institute> getAllInstitutes(){
		return instituteRepository.findAll();
	}
	
	
	public Page<Institute> listInstituteByPage(int pageNum){
		Pageable pageable = PageRequest.of(pageNum -1, INSTITUTES_PER_PAGE);
		return instituteRepository.findAll(pageable);
	}
	
	
	public void addInstitute(Institute institute) {
		instituteRepository.save(institute);
		
	}
	
	public Optional<Institute> getInstituteById(long id){
		return instituteRepository.findById(id);
	}
	
	public void deleteCustomer(long id) {
		instituteRepository.deleteById(id);
	}
	
	public long numberOfInstitute() {
		return instituteRepository.count();
	}
	
	
}

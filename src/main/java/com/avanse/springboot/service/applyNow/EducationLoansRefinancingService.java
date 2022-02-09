package com.avanse.springboot.service.applyNow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.forms.applyNow.EducationLoanRefinancing;
import com.avanse.springboot.repository.forms.applyNow.EducationLoansRefinancingRepository;

@Service
public class EducationLoansRefinancingService {

	@Autowired
	EducationLoansRefinancingRepository educationLoansRefinancingRepository;
	
	public static final int EDUCATION_LOANS_REFINANCING_LEADS_PER_PAGE = 4;
	
	public List<EducationLoanRefinancing> getAllEducationLoanRefinancingLeads(){
		return educationLoansRefinancingRepository.findAll();
	}
	
	public Page<EducationLoanRefinancing> listEducationLoanRefinancingLeadsPerPage(int pageNum){
		Pageable pageable = PageRequest.of(pageNum - 1 , EDUCATION_LOANS_REFINANCING_LEADS_PER_PAGE);
		return educationLoansRefinancingRepository.findAll(pageable);
	}
	
	public void addEducationLoanRefinancing(EducationLoanRefinancing educationLoanRefinancing) {
		educationLoansRefinancingRepository.save(educationLoanRefinancing);
		
		
	}
	
	public long numberOfEducationLoanRefinancingLeads() {
		return educationLoansRefinancingRepository.count();
	}
	
	
	
}

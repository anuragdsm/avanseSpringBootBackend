package com.avanse.springboot.service.applyNow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.forms.applyNow.EducationInstitutionLoan;
import com.avanse.springboot.repository.forms.applyNow.EducationInstitutionLoanRepository;

@Service
public class EducationInstitutionLoanService {

	@Autowired
	EducationInstitutionLoanRepository educationInstitutionLoanRepository;
	
	public static final int EDUCATION_INSTITUTION_LOAN_LEADS_PER_PAGE=4;
	
	public List<EducationInstitutionLoan> getAllEducationInstitutionLoanLeads(){
		return educationInstitutionLoanRepository.findAll();
	}
	
	public Page<EducationInstitutionLoan> listEducationInstitutionLoanLeadsPerPage(int pagenum){
	
		Pageable pageable = PageRequest.of(pagenum - 1, EDUCATION_INSTITUTION_LOAN_LEADS_PER_PAGE);
		return educationInstitutionLoanRepository.findAll(pageable);
	}
	
	public void addEducationInstitutionLoan(EducationInstitutionLoan educationInstitutionLoan) {
		educationInstitutionLoanRepository.save(educationInstitutionLoan);
	}
	
	public long numberOfEducationInstitutionLoanLeads() {
		return educationInstitutionLoanRepository.count();
	}
	
	
	
	
	
}

package com.avanse.springboot.service.applyNow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.forms.applyNow.ExecutiveEducationLoan;
import com.avanse.springboot.repository.forms.applyNow.ExecutiveEducationLoanRepository;

@Service
public class ExecutiveEducationLoanService {

	@Autowired
	ExecutiveEducationLoanRepository executiveEducationLoanRepository;
	
	public static final int  EXECUTIVE_EDUCATION_LOAN_LEADS_PER_PAGE = 4;
	
	public List<ExecutiveEducationLoan> getAllExecutiveEducationLoanLeads(){
		
		return executiveEducationLoanRepository.findAll();
		
	}
	
	public Page<ExecutiveEducationLoan> listExecutiveEducationLoanLeadsPerPage(int pageNum){
		Pageable pageable = PageRequest.of(pageNum -1 , EXECUTIVE_EDUCATION_LOAN_LEADS_PER_PAGE);
		return executiveEducationLoanRepository.findAll(pageable);
	}
	
	public void addExecutiveEducationLoan(ExecutiveEducationLoan executiveEducationLoan) {
		executiveEducationLoanRepository.save(executiveEducationLoan);
	}
	
	public long numberOfExecutiveEducationLoanLead() {
		return executiveEducationLoanRepository.count();
	}
	
	

}

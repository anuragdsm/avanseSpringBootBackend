package com.avanse.springboot.service.forms.contactUs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avanse.springboot.model.forms.contactUs.Investor;
import com.avanse.springboot.repository.forms.contactUs.InvestorRepository;

@Service
public class InvestorService {

	public static final int INVESTORS_PER_PAGE = 4;
	
	@Autowired
	InvestorRepository investorRepository;
	
	public List<Investor> getAllInvestors(){
	
		return investorRepository.findAll();
	}
	
	public Page<Investor> listInvestorsByPage(int pageNum){
		
		Pageable pageable = PageRequest.of(pageNum - 1, INVESTORS_PER_PAGE);
		return investorRepository.findAll(pageable);
	}
	
	
	public void addInvestor(Investor investor) {
		investorRepository.save(investor);
	}
	
	public Optional<Investor> getInvestorById(long id){
		return investorRepository.findById(id);
		
	}
	
	public void deleteInvestor(long id) {
		investorRepository.deleteById(id);
	}
	
	public long numberOfInvestors() {
		return investorRepository.count();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

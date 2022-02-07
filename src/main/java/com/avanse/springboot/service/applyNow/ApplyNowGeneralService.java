package com.avanse.springboot.service.applyNow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.forms.applyNow.ApplyNowGeneral;
import com.avanse.springboot.repository.forms.applyNow.ApplyNowGeneralRepository;

@Service
public class ApplyNowGeneralService {
	
	@Autowired
	ApplyNowGeneralRepository applyNowGeneralRepository;
	
	public static final int APPLY_NOW_GENERAL_LEADS_PER_PAGE = 4;
	
	public List<ApplyNowGeneral> getAllApplyNowGeneralLeads(){
		return applyNowGeneralRepository.findAll();
	}
	
	public Page<ApplyNowGeneral> listApplyNowGeneralLeadsPerPage(int pageNum){
		Pageable pageable = PageRequest.of(pageNum - 1 , APPLY_NOW_GENERAL_LEADS_PER_PAGE);
		return applyNowGeneralRepository.findAll(pageable);
	}
	
	public void addApplyNowGeneral(ApplyNowGeneral applyNowGeneral) {
		applyNowGeneralRepository.save(applyNowGeneral);
		
		
	}
	
	public long numberOfApplyNowGeneralLeads() {
		return applyNowGeneralRepository.count();
	}
	

}

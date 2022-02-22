package com.avanse.springboot.service.applyNow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.forms.applyNow.PartnerWithUs;
import com.avanse.springboot.repository.forms.applyNow.PartnerWithUsRepository;

@Service
public class PartnerWithUsService {

	@Autowired
	PartnerWithUsRepository partnerWithUsRepository;
	
	public List<PartnerWithUs> getPartnerWithUsLeads(){
		return partnerWithUsRepository.findAll();
	}
	
	public void addPartnerWithUsLead(PartnerWithUs partnerWithUs) {
		partnerWithUsRepository.save(partnerWithUs);
	}
	
	public long numberOfPartnerWithUsLeads() {
		return partnerWithUsRepository.count();
	}
	
}

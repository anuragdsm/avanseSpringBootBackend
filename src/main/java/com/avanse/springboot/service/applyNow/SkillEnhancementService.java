package com.avanse.springboot.service.applyNow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.forms.applyNow.SkillEnhancement;
import com.avanse.springboot.repository.forms.applyNow.SkillEnhancementRepository;
@Service
public class SkillEnhancementService {
	
	@Autowired
	SkillEnhancementRepository skillEnhancementRepository;
	
	public static final int SKILL_ENHANCEMENT_LEADS_PER_PAGE = 4;
	
	public List<SkillEnhancement> getAllSkillEnhancementsLeads(){
		
		return skillEnhancementRepository.findAll();
		
		
	}
	
	public Page<SkillEnhancement> listSkillEnhancementLeadsByPage(int pageNum){
		Pageable pageable = PageRequest.of(pageNum - 1 , SKILL_ENHANCEMENT_LEADS_PER_PAGE);
		return skillEnhancementRepository.findAll(pageable);
	}
	
	public void addSkillEnhancement(SkillEnhancement skillEnhancement) {
		skillEnhancementRepository.save(skillEnhancement);
		
	}
	
	public long numberOfSkillEnhancementLeads() {
		return skillEnhancementRepository.count();
	}
	

}

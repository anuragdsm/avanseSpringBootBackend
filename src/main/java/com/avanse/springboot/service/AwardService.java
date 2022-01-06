package com.avanse.springboot.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.Award;
import com.avanse.springboot.repository.AwardRepository;

@Service
public class AwardService {

	@Autowired
	AwardRepository awardRepository;
	
	
	public List<Award> getAllAwards(){
		return awardRepository.findAll();
		
	}
	
	@Transactional
	public void addAward(Award award) {
		awardRepository.save(award);
	}
	
	public Optional<Award> getAwardById(long id){
		return awardRepository.findById(id);
	}
	
	@Transactional
	public void deleteAward(long id) {
		awardRepository.deleteById(id);
	}
	
	
	public long numberOfAwards() {
		return awardRepository.count();
	}
	
	
	
	
}

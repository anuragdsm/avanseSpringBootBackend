package com.avanse.springboot.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.Award;
import com.avanse.springboot.repository.AwardRepository;

@Service
public class AwardService {

	public static final int AWARDS_PER_PAGE = 4;
	
	@Autowired
	AwardRepository awardRepository;
	
	
	public List<Award> getAllAwards(){
		return awardRepository.findAll();
		
	}
	
	public Page<Award> listAwardsByPage(int pageNum){
		Pageable pageable = PageRequest.of(pageNum - 1, AWARDS_PER_PAGE);
		return awardRepository.findAll(pageable);
	}
	
	/*
	 * public Page<Award> listAwardsByPageLatest(int pageNum){ Pageable pageable =
	 * PageRequest.of(pageNum - 1, AWARDS_PER_PAGE).withSort(Direction.DESC); return
	 * awardRepository.findAll(pageable); }
	 * 
	 */
	
	
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

package com.avanse.springboot.service.applyNow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.forms.applyNow.ETutoring;
import com.avanse.springboot.repository.forms.applyNow.ETutoringRepository;

@Service
public class ETutoringService {
	@Autowired
	ETutoringRepository eTutoringRepository;
	
	public static final int ETUTORING_LEADS_PER_PAGE = 4;
	
	public List<ETutoring> getAllETutoringLeads(){
		return eTutoringRepository.findAll();
	}
	
	public Page<ETutoring> listETutoringLeadsPerPage(int pagenum){
		Pageable pageable = PageRequest.of(pagenum - 1, ETUTORING_LEADS_PER_PAGE);
		return eTutoringRepository.findAll(pageable);
	}
	
	public void addETutoring(ETutoring eTutoring) {
		eTutoringRepository.save(eTutoring);
	}
	
	public long numberOfETutoring() {
		return eTutoringRepository.count();
	}

}

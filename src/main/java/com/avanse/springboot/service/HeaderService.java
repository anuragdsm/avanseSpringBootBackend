package com.avanse.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avanse.springboot.model.Header;
import com.avanse.springboot.model.Page;
import com.avanse.springboot.repository.HeaderRepository;

@Service
public class HeaderService {

	@Autowired
	HeaderRepository headerRepository;
	
	@Transactional
	public void addHeader(Header header) {
		headerRepository.save(header);
	}
	
	public Optional<Header> getHeaderById(long id){
		return headerRepository.findById(id);
	}
}

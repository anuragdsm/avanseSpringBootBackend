package com.avanse.springboot.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.Testimonial;
import com.avanse.springboot.repository.TestimonialRepository;

@Service
public class TestimonialService {
	
	@Autowired
	TestimonialRepository testimonialRepository;
	
	public List<Testimonial> getAllTestimonials(){
		return testimonialRepository.findAll();
	}
	
	@Transactional
	public void addTestimonial(Testimonial testimonial) {
		testimonialRepository.save(testimonial);
	}
	
	@Transactional
	public void removeTestimonialById(long id) {
		testimonialRepository.deleteById(id);
	}
	
	public Optional<Testimonial> getTestimonialById(long id){
		return testimonialRepository.findById(id);
	}
	
	public long numberOfTestimonials() {
		
		return testimonialRepository.count();
		
	}
	

}

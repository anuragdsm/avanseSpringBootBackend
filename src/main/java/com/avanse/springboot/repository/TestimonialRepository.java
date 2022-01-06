package com.avanse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.Testimonial;

public interface TestimonialRepository extends JpaRepository<Testimonial, Long>{

	
	
}

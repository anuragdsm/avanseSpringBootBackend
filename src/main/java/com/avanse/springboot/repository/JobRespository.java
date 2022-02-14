package com.avanse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.Job;

public interface JobRespository extends JpaRepository<Job, Long>{
//	Job findJobByLocation(String location);
	

}

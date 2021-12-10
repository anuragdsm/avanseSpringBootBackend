package com.avanse.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avanse.springboot.model.Job;
import com.avanse.springboot.repository.JobRespository;

@Service
public class JobService {

	@Autowired
	JobRespository jobRespository;
	
//	Return the list of all the jobs
	public List<Job> getAllJobs(){
		return jobRespository.findAll();
	}
	
//	Function to save the job in the database
	@Transactional
	public void addJob(Job job) {
		jobRespository.save(job);
	}
	
//	Function to remove the job from the database
	
	@Transactional
	public void removeJobById(Long id) {
		jobRespository.deleteById(id);
	}
	
//	Optional class to fetch the result on the same page
	
	public Optional<Job> getJobById(long id){
		return jobRespository.findById(id);
	}
	
//	Return the total number of jobs added in the database
	
	public long numberOfJobs() {
		return jobRespository.count();
	}
	
	
	
	
}

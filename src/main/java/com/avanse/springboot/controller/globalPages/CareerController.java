package com.avanse.springboot.controller.globalPages;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avanse.springboot.model.Job;
import com.avanse.springboot.repository.JobRespository;
import com.avanse.springboot.service.JobService;

@RestController
public class CareerController {
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private JobRespository jobRespository;
		
//	This was just an attempt will have to work on it

	  @CrossOrigin(origins = "*")
	  @GetMapping("/public/api/getAllJobs") 
	  public List<Job> getAllJobs() { 
		  System.out.println("====Getting job===="); // return
		   return jobService.getAllJobs();
	  
	  }
	  
	
}
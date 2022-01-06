package com.avanse.springboot.controller.globalPages;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import com.avanse.springboot.repository.JobRespository;
import com.avanse.springboot.service.JobService;

@RestController
public class CareerController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private JobRespository jobRespository;
	
	
//	This was just an attempt will have to work on it
	
	/*
	 * @CrossOrigin(origins = "http://localhost:8080")
	 * 
	 * @GetMapping("/viewDynamicPages/jobApplication") public Job job(@PathVariable
	 * Long id) { System.out.println("====Getting job===="); // return
	 * jobService.getJobById(id).get(); return new Job(jobService.);
	 * 
	 * }
	 */
}

package com.avanse.springboot;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.avanse.springboot.model.Course;
import com.avanse.springboot.model.University;
import com.avanse.springboot.repository.CourseRepository;
import com.avanse.springboot.repository.UniversityRepository;

@EnableAutoConfiguration
@SpringBootApplication
public class SpringbootBackendApplication {

	public static void main(String[] args) {
		
//		ConfigurableApplicationContext configurableApplicationContext =
		SpringApplication.run(SpringbootBackendApplication.class, args);
//		
//		UniversityRepository universityRepository = configurableApplicationContext.getBean(UniversityRepository.class);
//		CourseRepository courseRepository = configurableApplicationContext.getBean(CourseRepository.class);
	}
	
//	Is github connected via mac?	
	/*
	 * Initial test
	*/
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		
//		this.userRepository.save(new User ("Anuragdf", "Kumar", "anurag@digistreetmedia.in" ));
//		this.userRepository.save(new User ("Anuj", "Jain", "anuj@digistreetmedia.in" ));
//		this.userRepository.save(new User ("Abhi", "Gupta", "abhi@digistreetmedia.in" ));
//		this.userRepository.save(new User ("Amit", "Singh", "amit@digistreetmedia.in" ));
//		
//		
//	}
	
	/*
	 * Use the command line interface to test the values in the database. 
	 * Check if the mapping is working ok or not...
	 * 
	*/
	
//	@Autowired
//	UniversityRepository universityRepository;
//	
//	@Autowired
//	CourseRepository courseRepository;
//	
//	@Bean
//	CommandLineRunner runner() {
//			return args -> {
//				
//				University university1 = new University("Managalm","Gurgaon", "mangalam.jpg", "This is A grade University",2007, "Yes",null, null, true);
//				University university2 = new University("Lovely","Noida", "lovely.jpg", "This is B grade University",2009, "Yes",null, null, true);
//				
//				universityRepository.save(university1);
//				universityRepository.save(university2);
//				
//				Course course = new Course(null, new Date(), "Computers", "2 Years", "Basics Of programming", 32343.3, "GRE", "Aadhar", university1);
//				courseRepository.save(course);
//				
//				course = new Course(null, new Date(), "English", "4 Years", "English Grammar", 6343.3, "IELTS", "Aadhar", university2);
//					
//				courseRepository.save(course);
//				
//				course = new Course(null, new Date(), "Mechanical", "3 Years", "AutoCAD", 16343.3, "GMAT", "Licence", university2);
//				
//				courseRepository.save(course);
//
//				
//			};
//
//	}
//	
	
	
	

}

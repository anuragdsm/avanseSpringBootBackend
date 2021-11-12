package com.avanse.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.avanse.springboot.repository.CourseRepository;
import com.avanse.springboot.repository.UniversityRepository;


@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext configurableApplicationContext =
		SpringApplication.run(SpringbootBackendApplication.class, args);
		
		UniversityRepository universityRepository = configurableApplicationContext.getBean(UniversityRepository.class);
		CourseRepository courseRepository = configurableApplicationContext.getBean(CourseRepository.class);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
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

}

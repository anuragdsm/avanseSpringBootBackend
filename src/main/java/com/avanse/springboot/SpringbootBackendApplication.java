package com.avanse.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}

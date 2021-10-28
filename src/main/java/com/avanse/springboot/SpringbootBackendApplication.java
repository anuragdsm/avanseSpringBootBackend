package com.avanse.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.avanse.springboot.model.User;
import com.avanse.springboot.repository.UserRepository;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		this.userRepository.save(new User ("Anuragdf", "Kumar", "anurag@digistreetmedia.in" ));
		this.userRepository.save(new User ("Anuj", "Jain", "anuj@digistreetmedia.in" ));
		this.userRepository.save(new User ("Abhi", "Gupta", "abhi@digistreetmedia.in" ));
		
		
	}

}

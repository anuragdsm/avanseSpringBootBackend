package com.avanse.springboot.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.avanse.springboot.model.User;
import com.avanse.springboot.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class UserController {

	
//	Declare log4j object
	private static Logger log = Logger.getLogger(UserController.class); 

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("users")
	public List<User>getUsers(){	
		
//		Start appending the messages to the console
		
//		log.addAppender(app);
		log.debug("This is debug");
		log.info("This is info");
		log.error("This is error");
		log.fatal("This is fatal");
		log.warn("This is warn");
		log.fatal("All was fine until this");

		return this.userRepository.findAll();
	}
	
}

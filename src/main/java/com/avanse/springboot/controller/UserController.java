package com.avanse.springboot.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avanse.springboot.DTO.UserDTO;
import com.avanse.springboot.model.User;
import com.avanse.springboot.repository.UserRepository;
import com.avanse.springboot.service.CustomUserDetailService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000/")
@Controller
//@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

	
//	Declare log4j object
	private static Logger log = Logger.getLogger(UserController.class); 

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
//	@RequestMapping("users")
//	public List<User>getUsers(){	
//		
////		Start appending the messages to the console
//		
////		log.addAppender(app);
//		log.debug("This is debug");
//		log.info("This is info");
//		log.error("This is error");
//		log.fatal("This is fatal");
//		log.warn("This is warn");
//		log.fatal("All was fine until this");
//
//		return this.userRepository.findAll();
//	}
	
	
	/*
	 * Code for just open the register page
	*/
	
	
//	@GetMapping("/register")
//	public String registerNewUser() {
//		return "register";
//	}
//	
//	/*
//	 * One the form is opened using the getmapping,
//	 * now we will use the post mapping to process the form
//	*/
//	
//	@PostMapping("/registerUser")
////	@ResponseBody
//	public String registerNewUserPost(@ModelAttribute("userDTO") UserDTO userDTO) {
//		
//		User user = new User();
//		
////		user.setId(userDTO.getId());
//		
//		
////		customUserDetailService.
//		return "index";
//	}
//	
	
	
	
	
}

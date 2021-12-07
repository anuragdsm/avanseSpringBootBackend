/*
 * This class is going to be a general controller to controll the page linking...
 * 
*/

package com.avanse.springboot.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class PageController {

	@GetMapping("/home")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/careers")
	public String careerPage() {
		return "careers";
	}
	
	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}
	
}

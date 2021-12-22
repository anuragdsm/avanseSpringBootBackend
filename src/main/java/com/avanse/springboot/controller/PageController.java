/*
 * This class is going to be a general controller to controll the page linking...
 * 
*/

package com.avanse.springboot.controller;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.avanse.springboot.model.Page;
import com.avanse.springboot.service.PageService;

@Controller

public class PageController {
	
	@Autowired
	PageService pageService;

//	@GetMapping("/home")
//	public String homePage() {
//		return "home";
//	}
	
	@GetMapping("/careers")
	public String careerPage() {
		return "careers";
	}
	
	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}
	
//	To be reslved later to publish pages only on the basis of if the page is active or not
	
//	@GetMapping("/viewPages/{pageTitle}")
//	public Boolean checkIfPublished(@PathVariable String pageTitle) {
//		Page page = pageService.getPageByPageTitle(pageTitle).get();
//		Boolean check = page.getIsPageActive();
//		return check;
//	}
//	
	
	
//	@GetMapping("/viewPages/{pageTitle}")
//	public ModelAndView getAddedPagesCode(@PathVariable("pageTitle") String pageTitle) {
//		System.out.println("Into the addedPages Code get ");
//		ModelAndView modelAndView = new ModelAndView("addedPages/"+pageTitle);
//		return modelAndView;		
//	}
	@GetMapping("/viewPages/{extractedFileName}")
	public ModelAndView getAddedPage(@PathVariable("extractedFileName") String extractedFileName) {
		System.out.println("Into the addedPages Code get ");
		ModelAndView modelAndView = new ModelAndView("addedPages/"+extractedFileName);
		return modelAndView;		
	}
	
	
	
	
//	@GetMapping("{pageLink}")
//	public ModelAndView getAddedPage(@PathVariable("pageLink") String pageLink) {
//		System.out.println("Into the addedPages Code get ");
//		ModelAndView modelAndView = new ModelAndView("addedPages/" + pageLink);
//		return modelAndView;
//	}
	
//	
//	@GetMapping("/viewDynamicPages/{pageTitle}")
//	public ModelAndView getDynamicPages(@PathVariable("pageTitle") String pageTitle) {
//		System.out.println("Into the addedPages Code get ");
//		ModelAndView modelAndView = new ModelAndView("dynamicPages/"+pageTitle);
//		return modelAndView;		
//	}
	
	
	
	

	@GetMapping("/viewDynamicPages/{extractedFileName}")
	public ModelAndView getDynamicPage(@PathVariable("extractedFileName") String extractedFileName) {
		System.out.println("Into the get Dyanamic page and find file name by removing the extention ");
		
		ModelAndView modelAndView = new ModelAndView("dynamicPages/"+extractedFileName);
		return modelAndView;		
	}
	
	private String code(String pageName) {
		Page page = pageService.findPageByFilename(pageName);
		String consolidateCode = page.getConsolidatedHTMLCode();	
		System.out.println("THE CONSOLIDATED CODE IS " + consolidateCode); 
		return consolidateCode;
	}
	
	
	
}

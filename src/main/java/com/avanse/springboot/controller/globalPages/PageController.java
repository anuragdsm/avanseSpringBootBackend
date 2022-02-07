/*
 * This class is going to be a general controller to controll the page linking...
 * 
*/

package com.avanse.springboot.controller.globalPages;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.avanse.springboot.DTO.forms.contactUs.CustomerDTO;
import com.avanse.springboot.DTO.forms.contactUs.InstituteDTO;
import com.avanse.springboot.DTO.forms.contactUs.InvestorDTO;
import com.avanse.springboot.DTO.forms.contactUs.MediaDTO;
import com.avanse.springboot.model.Page;
import com.avanse.springboot.model.Post;
import com.avanse.springboot.model.PostCategory;
import com.avanse.springboot.service.PageService;
import com.avanse.springboot.service.PostCategoryService;
import com.avanse.springboot.service.PostService;

@Controller

public class PageController {
	
	@Autowired
	PageService pageService;
	
	@Autowired
	PostCategoryService postCategoryService;
	
	@Autowired
	PostService postService;
	
	@GetMapping("/careers")
	public String careerPage() {
		return "careers";
	}
	
	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}

	@GetMapping("/viewPages/{extractedFileName}")
	public ModelAndView getAddedPage(@PathVariable("extractedFileName") String extractedFileName) {
		ModelAndView modelAndView = new ModelAndView("addedPages/"+extractedFileName);
		return modelAndView;		
	}
	
	@GetMapping("/viewDynamicPages/{extractedFileName}")
	public ModelAndView getDynamicPage(@PathVariable("extractedFileName") String extractedFileName, Model model) {
		ModelAndView modelAndView = new ModelAndView("dynamicPages/"+extractedFileName);
		model.addAttribute("postCategories", postCategoryService.getAllPostCategories());
		model.addAttribute("posts", postService.getAllPosts());
		model.addAttribute("customerDTO", new CustomerDTO());
		model.addAttribute("instituteDTO", new InstituteDTO());
		model.addAttribute("investorDTO", new InvestorDTO());
		model.addAttribute("mediaDTO", new MediaDTO());
		return modelAndView;		
	}
	
	private String code(String pageName) {
		Page page = pageService.findPageByFilename(pageName);
		String consolidateCode = page.getConsolidatedHTMLCode();	
		System.out.println("THE CONSOLIDATED CODE IS " + consolidateCode); 
		return consolidateCode;
	}	
}

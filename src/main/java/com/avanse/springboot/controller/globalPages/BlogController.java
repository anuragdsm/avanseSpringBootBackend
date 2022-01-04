package com.avanse.springboot.controller.globalPages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.avanse.springboot.service.PostCategoryService;
import com.avanse.springboot.service.PostService;

@Controller
public class BlogController {

	@Autowired
	PostService postService;
	@Autowired
	PostCategoryService postCategoryService;
	
	@GetMapping("/viewPost/{extractedFileName}")
	public ModelAndView getAddedBlogPost(@PathVariable("extractedFileName") String extractedFileName, Model model) {
		System.out.println("Into the getAddBlogPost Get");
		ModelAndView modelAndView = new ModelAndView("addedBlogPosts/"+extractedFileName);
		model.addAttribute("postCategories", postCategoryService.getAllPostCategories());
		model.addAttribute("posts", postService.getAllPosts());


		return modelAndView;
	}
	
	
}
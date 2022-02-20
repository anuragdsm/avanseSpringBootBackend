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

import com.avanse.springboot.DTO.forms.applyNow.ApplyNowGeneralDTO;
import com.avanse.springboot.DTO.forms.applyNow.EducationInstitutionLoanDTO;
import com.avanse.springboot.DTO.forms.applyNow.ExecutiveEducationLoanDTO;
import com.avanse.springboot.DTO.forms.contactUs.CustomerDTO;
import com.avanse.springboot.DTO.forms.contactUs.InstituteDTO;
import com.avanse.springboot.DTO.forms.contactUs.InvestorDTO;
import com.avanse.springboot.DTO.forms.contactUs.MediaDTO;
import com.avanse.springboot.model.Page;
import com.avanse.springboot.model.Post;
import com.avanse.springboot.model.PostCategory;
import com.avanse.springboot.service.CourseService;
import com.avanse.springboot.service.PageService;
import com.avanse.springboot.service.PostCategoryService;
import com.avanse.springboot.service.PostService;
import com.avanse.springboot.service.UniversityService;

@Controller

public class PageController {
	
	@Autowired
	PageService pageService;
	
	@Autowired
	PostCategoryService postCategoryService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	UniversityService universityService;
	
	@GetMapping(value={"/index","/"})
	public String homePage() {
		return "dynamicPages/index";
	}
	@GetMapping("/career")
	public String careerPage() {
		return "dynamicPages/career";
	}
	@GetMapping("/career/apply")
	public String jobApplyPage() {
		return "dynamicPages/career";
	}
	
	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}
	
	@GetMapping("/courseDetail/{courseId}")
	public String courseDetailsPage(@PathVariable long courseId, Model model) {
		model.addAttribute("course", courseService.getCourseById(courseId).get());
		return "dynamicPages/courseDetail";
	}
	
	
	  @GetMapping(value =
	  {"/Country/{country}","/Country/{country}/{courseId}","/Country/{country}/UID={universityId}",
	  "/Country/{country}/{courseId}/{universityId}"}) public String
	  countryPage(@PathVariable("country") String countryName, Model model,
	  @PathVariable(name = "courseId",required = false) Long courseId,
	  @PathVariable(name = "universityId",required = false) Long universityId) {
	  
	  System.out.println("Testing Country --- > "+countryName);
	  System.out.println("Testing CourseId --- > "+courseId);
	  System.out.println("Testing UniversityID --- > "+universityId);
	  
	  if(courseId!=null) {
		  model.addAttribute("courseIdCheck", "courseIdIsPresent");
		  model.addAttribute("incomingCourseModel", courseService.getCourseById(courseId).get());
	  }
	  if(universityId!=null) {
		  model.addAttribute("universityIdCheck", "universityIdIsPresent");
		  model.addAttribute("incomingUniversityModel", universityService.getUniversityById(universityId).get());
	  }
	  
	  return "dynamicPages/"+countryName; 
	  }
	 
	@GetMapping("/admin/viewPages/{extractedFileName}")
	public ModelAndView getAddedPage(@PathVariable("extractedFileName") String extractedFileName) {
		ModelAndView modelAndView = new ModelAndView("addedPages/"+extractedFileName);	
		return modelAndView;		
	}
	
	@GetMapping("/viewDynamicPages/{extractedFileName}")
	public ModelAndView getDynamicPage(@PathVariable("extractedFileName") String extractedFileName, Model model) {
		ModelAndView modelAndView = new ModelAndView("dynamicPages/"+extractedFileName);
		
		if(extractedFileName.equals("blog")) {
			model.addAttribute("postCategories", postCategoryService.getAllPostCategories());
		model.addAttribute("posts", postService.getAllPosts());
		}
	
		model.addAttribute("customerDTO", new CustomerDTO());
		model.addAttribute("instituteDTO", new InstituteDTO());
		model.addAttribute("investorDTO", new InvestorDTO());
		model.addAttribute("mediaDTO", new MediaDTO());
//		model.addAttribute("applyNowGeneralDTO", new ApplyNowGeneralDTO());
		model.addAttribute("executiveEducationLoanDTO", new ExecutiveEducationLoanDTO());
		model.addAttribute("educationInstitutionLoanDTO", new EducationInstitutionLoanDTO());
		return modelAndView;		
	}
	
	/*
	 * private String code(String pageName) { Page page =
	 * pageService.findPageByFilename(pageName); String consolidateCode =
	 * page.getConsolidatedHTMLCode();
	 * System.out.println("THE CONSOLIDATED CODE IS " + consolidateCode); return
	 * consolidateCode; }
	 */
}

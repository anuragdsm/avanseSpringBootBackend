package com.avanse.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.avanse.springboot.DTO.CourseDTO;
import com.avanse.springboot.DTO.UniversityDTO;
import com.avanse.springboot.model.University;
import com.avanse.springboot.service.CourseService;
import com.avanse.springboot.service.UniversityService;

@Controller

public class AdminController {
	
	public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/images";
	public static String universityUploadDir = System.getProperty("user.dir")+"/src/main/resources/static/images/universityImages";
	
	
	
//	Variable of date type to rename the image to the current time stamp
	private Date date = new Date();
	
//	Get the milliseconds using the date object since 1970
	private long millisecsFrom1970 = date.getTime();
	
//	Convert the millisecs to String that can be pushed into the database
	private String modifiedFileNameByDate = String.valueOf(millisecsFrom1970);
	
//	public static String courseUploadDir = System.getProperty("user.dir")+"/src/main/resources/static/images";

	@Autowired
	UniversityService universityService;

	@Autowired
	CourseService courseService;

//	Function to show the admin home page.
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}

	
//	All function below are related to universities
	
//	Function to show the universities
	@GetMapping("/admin/universities")
	public String getUniversities(Model model) {
		model.addAttribute("universities", universityService.getAllUniversity());
		return "universities";
	}
	
//	Function to add a university
//	Need both get and post mapping for adding the university because the request could be of any type...
	
	@GetMapping("/admin/universities/add")
	public String universitiesAddGet(Model model) {
		model.addAttribute("universityDTO", new UniversityDTO());
//		model.addAttribute("university", new University());
		return "universitiesAdd";
	}
	
	
//	On writing the post mapping you will be able to upload images to the server.
	@PostMapping("/admin/universities/add")
	public String universitiesAddPost(@ModelAttribute("universityDTO")UniversityDTO universityDTO,
									@RequestParam("universityImage")MultipartFile file,
									@RequestParam("imgName") String imgName) throws IOException {
//		universityService.addUniversity(university);
		
//		Creating a new university object and a university dto object so that we can transfer the data from the dto to the main university model.
		University university = new University();
		
//		Passing data into the main university
		university.setId(universityDTO.getId());
		university.setName(universityDTO.getName());
		university.setLocation(universityDTO.getLocation());
		university.setEstablishedYear(universityDTO.getEstablishedYear());
		university.setIntakePeriod(universityDTO.getIntakePeriod());
		university.setAccomodation(universityDTO.getAccomodation());
		university.setApplicationProcess(universityDTO.getApplicationProcess());
		university.setDescription(universityDTO.getDescription());
		university.setImageName(universityDTO.getImageName());
		university.setUniversitySlug(universityDTO.getUniversitySlug());
				
//		Create the imageUUID and using the neo package get the filename and 
		String imageUUID;
		if (!file.isEmpty()) {
			
			imageUUID=file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(universityUploadDir,imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}
		else {
			imageUUID = imgName;
			
		}
		
//		Pass the UUID into the imagename of the university...
		
		university.setImageName(imageUUID);
		
//		Use the university Service to actually add and save the university.
		universityService.addUniversity(university);
		
		
		return "redirect:/admin/universities";
	}	
	
	
//	Function to delete a university by id
	@GetMapping("/admin/university/delete/{id}")
	public String deleteUniversity(@PathVariable long id) {
//		Need to create university object for this method so that we can use DTO object and pass the file name
		
		
		
//		String existingImageName=universityService.getUniversityById(id).filter(temp->temp.getId())
//		System.out.println(existingImageName);
		
		
		
//		deleteImageFromTemplate(id);
		
		/*
		 * Give the exact path where the file is located followed by a slash and then
		 * use the service method of get University by ID
		 */		
		
		
		File file = new File(universityUploadDir+"/"+universityService.getUniversityById(id));
		System.out.println(file.getAbsolutePath());
		
//		Check if the file exist before deleting and after deleting, just redirect to the universities page
		if(file.exists()) {
			file.delete();
		}
		
		universityService.removeUniversityById(id);
		return "redirect:/admin/universities";
	}
	
	
	
	/*
	Trying to define the deleteImage function and pass a id and model into it...
	*/
	
//	public void deleteImageFromTemplate(@PathVariable long id, Model model) {
//		model.addAttribute()
//	}
//	
	
	/*
	 * Here I have defined the update method for universities
		
	*/	
	
	
	@GetMapping("/admin/university/update/{id}")
	public String updateUniversity(@PathVariable long id, Model model) {
		University university = universityService.getUniversityById(id).get();
		UniversityDTO universityDTO = new UniversityDTO();
		
		universityDTO.setId(university.getId());
		universityDTO.setName(university.getName());
		universityDTO.setLocation(university.getLocation());
		universityDTO.setEstablishedYear(university.getEstablishedYear());
		universityDTO.setIntakePeriod(university.getIntakePeriod());
		universityDTO.setAccomodation(university.getAccomodation());
		universityDTO.setApplicationProcess(university.getApplicationProcess());
		universityDTO.setDescription(university.getDescription());
		universityDTO.setImageName(university.getImageName());
		universityDTO.setUniversitySlug(university.getUniversitySlug());
		
		
		
		/*
		 * university.setId(universityDTO.getId());
		 * university.setName(universityDTO.getName());
		 * university.setLocation(universityDTO.getLocation());
		 * university.setEstablishedYear(universityDTO.getEstablishedYear());
		 * university.setIntakePeriod(universityDTO.getIntakePeriod());
		 * university.setAccomodation(universityDTO.getAccomodation());
		 * university.setApplicationProcess(universityDTO.getApplicationProcess());
		 * university.setDescription(universityDTO.getDescription());
		 * university.setImageName(universityDTO.getImageName());
		 * university.setUniversitySlug(universityDTO.getUniversitySlug());
		 */
		model.addAttribute("universityDTO", universityDTO);
		return "universitiesAdd";
	}
	
	
	/*
	 * All Function below are related to courses Function to show the courses
	 */
	
	@GetMapping("/admin/courses")
	public String getCourses(Model model) {
		model.addAttribute("courses", courseService.getAllCourses());
		return "courses";
	}
	

	/*
	 * Function to add a course
	 * Again we will need to create both get and post mapping
	 * 
	*/
	
	@GetMapping("/admin/courses/add")
	public String coursesAddGet(Model model) {
		model.addAttribute("courseDTO",new CourseDTO());
		return "coursesAdd";
	}
	
}



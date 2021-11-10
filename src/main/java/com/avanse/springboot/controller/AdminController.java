package com.avanse.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
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
import com.avanse.springboot.model.Course;
import com.avanse.springboot.model.University;
import com.avanse.springboot.service.CourseService;
import com.avanse.springboot.service.UniversityService;

@Controller

public class AdminController {

	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images";
	public static String universityUploadDir = System.getProperty("user.dir")
			+ "/src/main/resources/static/images/universityImages";

	/*
	 * Variable of date type to rename the image to the current time stamp
	 */
	

	@Autowired
	UniversityService universityService;

	@Autowired
	CourseService courseService;

	/*
	 * Method to show the admin home page.
	 */
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}

	/*
	 * ================================================ 
	 * All methods below are related to universities 
	 * ================================================
	 */

	/*
	 * Method to show the universities.html
	 */
	@GetMapping("/admin/universities")
	public String getUniversities(Model model) {
		model.addAttribute("universities", universityService.getAllUniversity());
		return "universities";
	}

	/*
	 * Method to add a university Need both get and post mapping for adding the
	 * university because the request could be of any type...
	 */
	@GetMapping("/admin/universities/add")
	public String universitiesAddGet(Model model) {
		model.addAttribute("universityDTO", new UniversityDTO());
//		model.addAttribute("university", new University());
		return "universitiesAdd";
	}

	/*
	 * On writing the post mapping you will be able to upload images to the server.
	 */
	@PostMapping("/admin/universities/add")
	public String universitiesAddPost(@ModelAttribute("universityDTO") UniversityDTO universityDTO,
			@RequestParam("universityImage") MultipartFile file, @RequestParam("imgName") String imgName)
			throws IOException {
//		universityService.addUniversity(university);

		/*
		 * Creating a new university object and a university dto object so that we can
		 * transfer the data from the dto to the main university model.
		 */
		University university = new University();

		/*
		 * Just after the university object is create, initialise the date object
		 * and then get the milliseconds in long
		 * Now convert this milliseconds in the String format and store it in the variable
		 * which can be used further
		*/
		Date date = new Date();

		/*
		 * Get the milliseconds using the date object since 1970
		 */

		long millisecsFrom1970 = date.getTime();

		/*
		 * Convert the millisecs to String that can be pushed into the database
		 */
		
		String modifiedFileNameByDate = String.valueOf(millisecsFrom1970);
	
		
		/*
		 * Passing data into the main university
		 */
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

		/*
		 * Create the imageUUID and using the nio package get the filename and the path
		 */
		String imageUUID;
		if (!file.isEmpty()) {
			
			/*
			 * Extract The file extention using get Extention method and store in the ext variable.			
			*/			
			String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			
			/*
			 * Generate the complete file name with extention
			*/
			imageUUID = modifiedFileNameByDate+'.'+ext;
			
			/*
			 * Use the nio library to do the stream operations. Paas the universal Unique ID and the university upload Directory
			*/
			Path fileNameAndPath = Paths.get(universityUploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());	
			
		} else {
			imageUUID = imgName;

		}

		/*
		 * Pass the UUID into the imagename of the university...
		 */
		university.setImageName(imageUUID);

		/*
		 * Use the university Service to finally add and save the university.
		 */
		universityService.addUniversity(university);

		return "redirect:/admin/universities";
	}
	
	/*
	 * Function to delete a university by id
	 */
	@GetMapping("/admin/university/delete/{id}")
	public String deleteUniversity(@PathVariable long id) {

		/*
		 * Calling the delete Image function before this record can be deleted from the database, 
		 * otherwise we will never be able to access its name in future
		*/
		deleteImageFromStaticFolder(id);
		universityService.removeUniversityById(id);
		return "redirect:/admin/universities";
	}

	
	
	/*
	 * Function to delete the image from the server before it can be deleted from
	 * the database...
	 */

	public void deleteImageFromStaticFolder(@PathVariable long id) {

		University universityImageToBeDeleted = universityService.getUniversityById(id).get();
//		UniversityDTO universityDTO = new UniversityDTO();

		String myFile = universityImageToBeDeleted.getImageName();

		/*
		 * Give the exact path where the file is located followed by a slash and then
		 * use the service method of get University by ID
		 */

		File file = new File(universityUploadDir + "/" + myFile);
//		System.out.println(file.getAbsolutePath());

		/*
		 * Check if the file exist before deleting and after deleting
		 */
		if (file.exists())file.delete();
	}

	/*
	 * Here I have defined the update method.
	 * For universities we have to set the dto and pass the model
	 */

	@GetMapping("/admin/university/update/{id}")
	public String updateUniversity(@PathVariable long id, Model model) {
		University university = universityService.getUniversityById(id).get(); 
		UniversityDTO universityDTO = new UniversityDTO();
		
		/*
		 * While update, the old image is to be removed... Hence calling deleteimage from static method
		*/
		deleteImageFromStaticFolder(id);

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

		model.addAttribute("universityDTO", universityDTO);
		return "universitiesAdd";
	}

	/*
	 * ========================================== All Function below are related to
	 * courses ===========================================
	 */

	/*
	 * Function to show the courses
	 */
	@GetMapping("/admin/courses")
	public String getCourses(Model model) {
		model.addAttribute("courses", courseService.getAllCourses());
		return "courses";
	}

	/*
	 * Function to add a course Again we will need to create both get and post
	 * mapping
	 * 
	 */

	@GetMapping("/admin/courses/add")
	public String coursesAddGet(Model model) {
		model.addAttribute("courseDTO", new CourseDTO());
		return "coursesAdd";
	}

	/*
	 * Here implementing the post mapping for courses add
	 */

	@PostMapping("/admin/courses/add")
	public String coursesAddPost(@ModelAttribute("courseDTO") CourseDTO courseDTO) {

		/*
		 * Use the model attribute to transfer the data from course DTO to course object
		 */
		Course course = new Course();
		course.setId(courseDTO.getId());
		course.setTitle(courseDTO.getTitle());
		course.setDescription(courseDTO.getDescription());
		course.setDuration(courseDTO.getDuration());
		course.setDocumentsRequired(courseDTO.getDocumentsRequired());
		course.setExams(courseDTO.getExams());
		course.setFees(courseDTO.getFees());
		course.setWriteup(courseDTO.getWriteup());

		/*
		 * Now use the courseService to add the course
		 */

		courseService.addCourse(course);

		return "redirect:/admin/courses";
	}

	/*
	 * Function to delete a course by its id
	 */
	@GetMapping("/admin/course/delete/{id}")
	public String deleteCourse(@PathVariable long id) {
		courseService.deleteCourse(id);
		return "redirect:/admin/courses";
	}

	/*
	 * Method to update a course by its id Set DTO and pass the model as an
	 * argument
	 */

	@GetMapping("/admin/course/update/{id}")
	public String updateCourse(@PathVariable long id, Model model) {
		Course course = courseService.getCourseById(id).get();
		CourseDTO courseDTO = new CourseDTO();

		courseDTO.setId(course.getId());
		courseDTO.setTitle(course.getTitle());
		courseDTO.setDescription(course.getDescription());
		courseDTO.setDocumentsRequired(course.getDocumentsRequired());
		courseDTO.setDuration(course.getDuration());
		courseDTO.setExams(course.getExams());
		courseDTO.setFees(course.getFees());
		courseDTO.setWriteup(course.getWriteup());

		model.addAttribute("courseDTO", courseDTO);
		return "coursesAdd";
	}
}

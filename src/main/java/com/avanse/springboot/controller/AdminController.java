package com.avanse.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.avanse.springboot.DTO.CourseDTO;
import com.avanse.springboot.DTO.PageDTO;
import com.avanse.springboot.DTO.UniversityDTO;
import com.avanse.springboot.model.Course;
import com.avanse.springboot.model.Page;
import com.avanse.springboot.model.University;
import com.avanse.springboot.repository.CourseRepository;
import com.avanse.springboot.repository.PageRepository;
import com.avanse.springboot.repository.UniversityRepository;
import com.avanse.springboot.service.CourseService;
import com.avanse.springboot.service.PageService;
import com.avanse.springboot.service.UniversityService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AdminController {

	public static String imageUploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images";
	public static String universityUploadDir = System.getProperty("user.dir")
			+ "/src/main/resources/static/images/universityImages";

	public static String newPageAddDir = System.getProperty("user.dir") + "/src/main/resources/templates/addedPages";

	@Autowired
	UniversityService universityService;

	@Autowired
	CourseService courseService;

	@Autowired
	UniversityRepository universityRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	PageRepository pageRepository;

	@Autowired
	PageService pageService;

	/*
	 * Method to show the admin home page.
	 */
	@GetMapping("/admin")
	public String adminDashboard() {

		Long noOfUniversities = universityService.numberOfUniversities();
		System.out.println("Number of University is " + noOfUniversities);

		return "adminDashboard";
	}

	@GetMapping("/admin/manage")
	public String adminHome() {
		return "adminHome";
	}

	/*
	 * ================================================ All methods below are
	 * related to universities ================================================
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
		 * Just after the university object is create, initialise the date object and
		 * then get the milliseconds in long Now convert this milliseconds in the String
		 * format and store it in the variable which can be used further
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

		/*
		 * Create the imageUUID and using the nio package get the filename and the path
		 */
		String imageUUID;
		if (!file.isEmpty()) {

			/*
			 * Extract The file extention using get Extention method and store in the ext
			 * variable.
			 */
			String ext = FilenameUtils.getExtension(file.getOriginalFilename());

			/*
			 * Generate the complete file name with extention
			 */
			imageUUID = modifiedFileNameByDate + '.' + ext;

			/*
			 * Use the nio library to do the stream operations. Paas the universal Unique ID
			 * and the university upload Directory
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
		 * Before deleting the object, check if the university exist or not and for that
		 * you will need university repository.
		 * 
		 * Calling the delete Image function before this record can be deleted from the
		 * database, otherwise we will never be able to access its name in future
		 * 
		 */

		if (universityRepository.findById(id).isPresent()) {
			deleteImageFromStaticFolder(id);
			universityService.removeUniversityById(id);
		}

		else {
			System.out.println("Cannot Delete the object, Later to be displayed over the page");

		}

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
		if (file.exists())
			file.delete();
	}

	/*
	 * Here I have defined the update method. For universities we have to set the
	 * dto and pass the model
	 */

	@GetMapping("/admin/university/update/{id}")
	public String updateUniversity(@PathVariable long id, Model model) {
		University university = universityService.getUniversityById(id).get();
		UniversityDTO universityDTO = new UniversityDTO();

		/*
		 * While update, the old image is to be removed... Hence calling deleteimage
		 * from static method
		 */

		universityDTO.setId(university.getId());
		universityDTO.setName(university.getName());
		universityDTO.setLocation(university.getLocation());
		universityDTO.setEstablishedYear(university.getEstablishedYear());
		universityDTO.setIntakePeriod(university.getIntakePeriod());
		universityDTO.setAccomodation(university.getAccomodation());
		universityDTO.setApplicationProcess(university.getApplicationProcess());
		universityDTO.setDescription(university.getDescription());
		universityDTO.setImageName(university.getImageName());

		model.addAttribute("universityDTO", universityDTO);
		
		deleteImageFromStaticFolder(id);

		
		
		return "universitiesAdd";
	}

	/*
	 * Show university mapped courses
	 */

	@GetMapping("/admin/university/courses/{id}")
	public String getUniversityMappedCourses(Model model) {
		model.addAttribute("courses", courseService.getAllCourses());
		return "courses";
	}

	/*
	 * Function to populate university list in drop down...
	 */

	// @ModelAttribute("universityDTO") UniversityDTO universityDTO

	public void preLoadUniversity(Model model) {
		List<University> myUniversityList = universityService.getAllUniversity();
		model.addAttribute("universities", myUniversityList);
	}

	// Activate Deactivate university
	@GetMapping("/admin/activateDeactivateUniversity/{id}/{action}")
	@ResponseBody
	@CrossOrigin("*")
	public String activateDeactivateUniversity(@PathVariable(name = "id") long id, @PathVariable String action) {
		System.out.println("Requested for University action = " + action + " for University id= " + id);

		if (action.equals("ActivateUniversity")) {
			University university = universityService.getUniversityById(id).get();
			university.setIsUniversityActive(true);
			universityService.addUniversity(university);
			return "University Activated!!";
		}

		else {
			University university = universityService.getUniversityById(id).get();
			university.setIsUniversityActive(false);
			universityService.addUniversity(university);
			return "University De-Activated!!";
		}
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
//		Course course = new Course();

		/*
		 * Pass the university list to the dropdown on courseAdd.html
		 */

		University university = new University();
		model.addAttribute("university", university);

		List<University> universities = universityService.getAllUniversity();

		System.out.println(universities.toString());

		model.addAttribute("universities", universities);

		/*
		 * Now use the course service to actually add the object
		 */

//		courseService.addCourse(course);

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

//		University university = new University();
		Course course = new Course();
		course.setId(courseDTO.getId());
		course.setTitle(courseDTO.getTitle());
		course.setDescription(courseDTO.getDescription());
		course.setDuration(courseDTO.getDuration());
		course.setDocumentsRequired(courseDTO.getDocumentsRequired());
		course.setExams(courseDTO.getExams());
		course.setFees(courseDTO.getFees());
//		university.addTheCourse(course);
		course.setUniversity(courseDTO.getUniversity());

		courseService.addCourse(course);

		System.out.println(course.toString());

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
	 * Method to update a course by its id Set DTO and pass the model as an argument
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
//		course.setUniversity(courseDTO.getUniversity());

		model.addAttribute("courseDTO", courseDTO);
		return "coursesAdd";
	}

	/*
	 * ========================================== All Function below are related to
	 * pages ===========================================
	 */

	/*
	 * Method to show the pages.html
	 * 
	 */

	@GetMapping("/admin/pages")
	public String getPages(Model model) {
		model.addAttribute("pages", pageService.getAllPages());
		return "pages";
	}

	/*
	 * Method to add a page but need implement both get and post mapping for adding
	 * the university
	 * 
	 */

	@GetMapping("/admin/pages/add")
	public String pagesAddGet(Model model) {
		model.addAttribute("pageDTO", new PageDTO());
		return "pagesAdd";
	}

	/*
	 * The post mapping will be able to create a new file at the run time
	 * 
	 */

	@ResponseBody
	@PostMapping("/admin/pages/add")
	public String pagesAddPost(@ModelAttribute("pageDTO") PageDTO pageDTO) {

		Page page = new Page();

		page.setId(pageDTO.getId());
		page.setPageTitle(pageDTO.getPageTitle());
		page.setBannerHeading(pageDTO.getBannerHeading());
		page.setBannerSubHeading(pageDTO.getBannerSubHeading());
		
//		page.setPageLayout(pageDTO.getPageLayout());
		page.setMainSection(pageDTO.getMainSection());
		
		page.setBannerImageName(pageDTO.getBannerImageName());
		page.setBannerImageAlt(pageDTO.getBannerImageAlt());
		page.setCssCode(pageDTO.getCssCode());
		page.setJsCode(pageDTO.getJsCode());

		page.setMetaTitle(pageDTO.getMetaTitle());
		page.setMetaKeyword(pageDTO.getMetaKeyword());

		page.setMetaDescription(pageDTO.getMetaDescription());

		/*
		 * Creating a new html template
		 */

		String extention = ".html";

		/*
		 * Before creating the html file, we have to ensure that two files do not get
		 */

		String htmlFileName = pageDTO.getPageTitle().replaceAll(" ","-").toLowerCase();
		List<Page> allPages = pageRepository.findAll();
		Iterator<Page> iterator = allPages.iterator();

		/*
		 * Rename the file if the file with the same name already exist
		 */
		
		int count = 0;
		while (iterator.hasNext()) {
			Page pageUnderEvaluation = iterator.next();
			if (pageUnderEvaluation.getMetaTitle().equalsIgnoreCase(htmlFileName)) {
				htmlFileName += ++count;
			}
		} //Problem will occur when user will enter 3 the same name for more than 2 times...
		// Some code will have to be written to handle this problem using string and regex manipulation
		
//		htmlFileName.

		htmlFileName += extention;

		try {
			Path fileNameAndPath = Paths.get(newPageAddDir, htmlFileName);
			Files.createFile(fileNameAndPath);

		} catch (IOException e) {
			// TODO: handle exception

		}
		
		/*
		 * Now save the file name in the database so as to access the 
		 * file in the future while updating...
		 * Searching with the exact file name will be required.
		*/
		pageDTO.setFileName(htmlFileName);
		page.setFileName(pageDTO.getFileName());
		
		pageService.addPage(page);
		
		

	
//		htmlPage

		
		
		/*
		 * Logic for adding the content in the file to be over here
		 * It the end publish the page...
		*/
		
		String codeInFile = htmlBoilerPlate(pageDTO.getMetaTitle(),
											pageDTO.getBannerHeading(), 
											pageDTO.getBannerSubHeading(),
											pageDTO.getMetaDescription(),
											pageDTO.getMainSection(),
											pageDTO.getCssCode());
		
		System.out.println("The following code will be there in the file "+codeInFile);
		
		pageDTO.setConsolidatedHTMLCode(codeInFile);
		page.setConsolidatedHTMLCode(pageDTO.getConsolidatedHTMLCode());
		
		Date lastModifiedDate = new Date();
		
		pageDTO.setLastModified(lastModifiedDate);
		page.setLastModified(pageDTO.getLastModified());
		
		pageService.addPage(page);
		
		try {
			pushCodeInFile(codeInFile, pageDTO.getFileName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "page Added sucessfully" + codeInFile;
//		  return "redirect:/admin/pages";

	}

	private void pushCodeInFile(String codeInFile, String fileName) throws IOException {
		// TODO Auto-generated method stub
				
		Path fileNameAndPath = Paths.get(newPageAddDir, fileName);
		Files.write(fileNameAndPath, codeInFile.getBytes());
		
	}

	private String htmlBoilerPlate(String metaTitle, String bannerHeading, String bannerSubheading, String metaDescription, String mainSection, String cSSCode) {
		// TODO Auto-generated method stub
		
		/*
		 * initial code
		*/
		String initCode;
		initCode = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "  <head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <meta name=\"description\" content=\""+metaDescription+"\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n"
				+ "    <title>"+metaTitle+"</title>\r\n"
				+ "    <h1>"+bannerHeading+"</h1>\r\n"
				+ "    <h2>"+bannerSubheading+"</h2>\r\n"
				+ "    <link rel=\"stylesheet\" href=\"css/sb-admin-2.min.css\">\r\n"
				+ "    <style>\r\n"+cSSCode+"\r\n"+"</style>"
				+ "  </head>\r\n"
				+ "  <body>\r\n"
				+ mainSection 
				+ "  \r\n"
				+ "  </body>\r\n"
				+ "  </html>";
		return initCode;
	}
	
	/*
	 * Funtion to delete a page from the database and the server
	 * 
	*/
	
	@GetMapping("/admin/page/delete/{id}")
	public String deletePage(@PathVariable long id) {
		
		/*
		 * Basic check if the page already exist or not
		*/
		
		if(pageRepository.findById(id).isPresent()) {
			deleteHtmlFileFromAddedPagesFolder(id);
			pageService.removePageById(id);
		}
		
		else {
			System.out.println("Cannot delete the page");
		}
		return "redirect:/admin/pages";
		
	}

	private void deleteHtmlFileFromAddedPagesFolder(@PathVariable long id) {
		// TODO Auto-generated method stub
		Page htmlFileToBeDeleted = pageService.getPageById(id).get();
		String theFile = htmlFileToBeDeleted.getFileName();	
		File file = new File(newPageAddDir + "/" + theFile);		
		if(file.exists())file.delete();
	}
	
	/*
	 * Write a function to write the code into the html file
	 * This function will be called in both add and edit function...
	 * We Dont need to call the write to file using edit
	 * becuase edit will eventually be called from only pages add...
	 * But keeping it in a function is a better idea, any day...
	*/
	
	
	@GetMapping("/admin/page/edit/{id}")
	public String editPage(@PathVariable long id, Model model) {
		Page page = pageService.getPageById(id).get();
		PageDTO pageDTO = new PageDTO();
		
		
		pageDTO.setId(page.getId());
		pageDTO.setPageTitle(page.getPageTitle());
		pageDTO.setBannerHeading(page.getBannerHeading());
		pageDTO.setBannerSubHeading(page.getBannerSubHeading());
//		page.setPageLayout(pageDTO.getPageLayout());
		pageDTO.setMainSection(page.getMainSection());
		pageDTO.setBannerImageName(page.getBannerImageName());
		pageDTO.setBannerImageAlt(page.getBannerImageAlt());
		pageDTO.setCssCode(page.getCssCode());
		pageDTO.setJsCode(page.getJsCode());
		pageDTO.setMetaTitle(page.getMetaTitle());
		pageDTO.setMetaKeyword(page.getMetaKeyword());
		pageDTO.setMetaDescription(page.getMetaDescription());
		model.addAttribute("pageDTO", pageDTO);	
		return "pagesAdd";
	}
	
	
	
//	End of class
}

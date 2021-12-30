package com.avanse.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.internal.build.AllowSysOut;
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
import com.avanse.springboot.DTO.ImageDTO;
import com.avanse.springboot.DTO.JobDTO;
import com.avanse.springboot.DTO.LocationDTO;
import com.avanse.springboot.DTO.PageDTO;
import com.avanse.springboot.DTO.PostDTO;
import com.avanse.springboot.DTO.UniversityDTO;
import com.avanse.springboot.model.Course;
import com.avanse.springboot.model.Header;
import com.avanse.springboot.model.Image;
import com.avanse.springboot.model.Job;
import com.avanse.springboot.model.Location;
import com.avanse.springboot.model.Page;
import com.avanse.springboot.model.Post;
import com.avanse.springboot.model.PostCategory;
import com.avanse.springboot.model.University;
import com.avanse.springboot.repository.CourseRepository;
import com.avanse.springboot.repository.HeaderRepository;
import com.avanse.springboot.repository.ImageRepository;
import com.avanse.springboot.repository.JobRespository;
import com.avanse.springboot.repository.LocationRepository;
import com.avanse.springboot.repository.PageRepository;
import com.avanse.springboot.repository.PostCategoryRepository;
import com.avanse.springboot.repository.PostRepository;
import com.avanse.springboot.repository.UniversityRepository;
import com.avanse.springboot.service.CourseService;
import com.avanse.springboot.service.HeaderService;
import com.avanse.springboot.service.ImageService;
import com.avanse.springboot.service.JobService;
import com.avanse.springboot.service.LocationService;
import com.avanse.springboot.service.PageService;
import com.avanse.springboot.service.PostCategoryService;
import com.avanse.springboot.service.PostService;
import com.avanse.springboot.service.UniversityService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AdminController {

//	public static String imageUploadDir = System.getProperty("user.dir") + "\\src\\mainresources\\static\\images";
	public static String universityUploadDir = System.getProperty("user.dir")
			+ "/src/main/resources/static/images/universityImages";

	public static String newPageAddDir = System.getProperty("user.dir") + "\\src\\main\\resources\\templates\\addedPages";
	public static String newPostAddDir = System.getProperty("user.dir") + "\\src\\main\\resources\\templates\\addedBlogPosts";
	
	public static String userAddedImagesDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\userAddedImages";
	public static String cssCodeFileDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\viewPagesAssets\\css";
	public static String jsCodeFileDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\viewPagesAssets\\js";
	
	

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CourseService courseService;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	ImageService imageService;
	
	
	
	@Autowired
	JobRespository jobRespository;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	LocationService locationService;

	@Autowired
	PageRepository pageRepository;

	@Autowired
	PageService pageService;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	PostService  postService;
	
	@Autowired
	PostCategoryRepository postCategoryRepository;
	
	@Autowired
	PostCategoryService postCategoryService;
	
	
	@Autowired
	UniversityRepository universityRepository;
	
	@Autowired
	UniversityService universityService;
	
	@Autowired
	HeaderRepository headerRepository;
	
	@Autowired
	HeaderService headerService;

	/*
	 * Method to show the admin home page.
	 */
	@GetMapping("/admin")
	public String adminDashboard(Model model) {

		Long noOfUniversities = universityService.numberOfUniversities();
		Long noOfCourses= courseService.numberOfCourses();
		Long noOfPages = pageService.numberOfPages();
		System.out.println("Number of University is " + noOfUniversities);
		model.addAttribute("numOfUniversities",noOfUniversities);
		model.addAttribute("numOfCourses",noOfCourses);
		model.addAttribute("numOfPages",noOfPages);

		System.out.println("User Added Image Directory is "+ userAddedImagesDir);
		return "adminDashboard";
	}

	@GetMapping("/admin/manage")
	public String adminHome() {
		return "adminHome";
	}

	/*
	 * ===================All methods below are related to universities ================================================
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
	public String deleteUniversity(@PathVariable long id){

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

	
	/* 14th December 2021 -> To be adressed soon
	 * 
	 * 
	 * @GetMapping("/admin/university/courses/{id}") public String
	 * getUniversityMappedCourses(Model model, @PathVariable long id) {
	 * model.addAttribute("courses", courseService.getCourseById(id)); return
	 * "courses"; }
	 * 
	 */	
	

	/*
	 * Function to populate university list in drop down...
	 */

	// @ModelAttribute("universityDTO") UniversityDTO universityDTO

	public void preLoadUniversity(Model model) {
		List<University> myUniversityList = universityService.getAllUniversity();
		model.addAttribute("universityList", myUniversityList);
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
//	public String coursesAddPost(@ModelAttribute("courseDTO") CourseDTO courseDTO, @PathVariable("universityDTO") UniversityDTO universityDTO) {
	public String coursesAddPost(@ModelAttribute("courseDTO") CourseDTO courseDTO, @RequestParam("university_id") long university_id) {

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
		course.setUniversity(universityService.getUniversityById(university_id).get());

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
		courseDTO.setUniversity(courseDTO.getUniversity());
		model.addAttribute("courseDTO", courseDTO);
		return "coursesAdd";
	}
	
	
	
	
	
	
	
	
	
	/*
	 * ===========All Function below are related to images================
	 */
	
	/*
	 * Method to show the images.html
	 */
	
	@GetMapping("/admin/images")
	public String getImages(Model model) {
		model.addAttribute("images", imageService.getAllImages());
		return "images";
	}
	

	@GetMapping("/admin/images/add")
	public String imagesAddGet(Model model) {
		model.addAttribute("imageDTO", new ImageDTO());
//		Course course = new Course();
		
		/*
		 * Pass the university list to the dropdown on courseAdd.html
		 */
		
		Image image = new Image();
		model.addAttribute("image", image);
		List<Image> images= imageService.getAllImages();
		System.out.println(images.toString());
		model.addAttribute("images", images);

		/*
		 * Now use the course service to actually add the object
		 */

//		courseService.addCourse(course);

		return "imagesAdd";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * ===========All Function below are related to jobs ================
	 */

	/*
	 * Method to show the jobs.html
	 */
	@GetMapping("/admin/jobs")
	public String getJobs(Model model) {
		model.addAttribute("jobs", jobService.getAllJobs());
		return "jobs";
	}
	
	/*
	 * Method to add a job. For that we will need both the get and post 
	 * mapping. Get mapping to open the form 
	 * Post mapping to send the data from the form and process it via controller
	*/
	
	@GetMapping("/admin/jobs/add")
	public String jobsAddGet(Model model) {
		model.addAttribute("jobDTO", new JobDTO());
		model.addAttribute("locations", locationService.getAllLocations());
		return "jobsAdd";
	}
	
	@PostMapping("/admin/jobs/add")
	public String jobsAddPost(@ModelAttribute("jobDTO") JobDTO jobDTO, @RequestParam("selectedLocations") String[] locationsIds) {
		Job job = new Job();
		job.setId(jobDTO.getId());
		job.setTitle(jobDTO.getTitle());
		job.setDescription(jobDTO.getDescription());
		job.setPostedBy(jobDTO.getPostedBy());
		
		for(String s: locationsIds) {
			Location location = locationRepository.getById(Long.valueOf(s));
			location.getJobs().add(job);
			locationRepository.save(location);
		}
		
		return "redirect:/admin/jobs";
	}
	
	/*
	 * ===========All Function below are related to office location ==============
	 */

	/*
	 * Method to show the locations.html
	 * 
	 */
	
	@GetMapping("/admin/locations")
	public String getLocations(Model model) {
		model.addAttribute("locations", locationService.getAllLocations());
		return "locations";
	}

	/*
	 * Add get and post mapping for showing and doing some processing after submitting the form
	*/
	
	@GetMapping("/admin/locations/add")
	public String locationAddGet(Model model) {
		model.addAttribute("locationDTO", new LocationDTO());
		return "locationsAdd";
	}
	
	@PostMapping("/admin/locations/add")
	public String locationAddPost(@ModelAttribute ("locationDTO") LocationDTO locationDTO) {
		
		Location location = new Location();
		location.setId(locationDTO.getId());
		location.setCity(locationDTO.getCity());

		locationService.addLocation(location);
		return "redirect:/admin/locations";
	}
	
	/*
	 * ===========All Function below are related to pages =============
	 */

	/*
	 * Method to show the pages.html
	 * 
	 */
	
	
	/*
	 * @GetMapping("/admin") public String adminDashboard(Model model) {
	 * 
	 * Long noOfUniversities = universityService.numberOfUniversities(); Long
	 * noOfCourses= courseService.numberOfCourses(); Long noOfPages =
	 * pageService.numberOfPages();
	 * 
	 * System.out.println("Number of University is " + noOfUniversities);
	 * 
	 * model.addAttribute("numOfUniversities",noOfUniversities);
	 * model.addAttribute("numOfCourses",noOfCourses);
	 * model.addAttribute("numOfPages",noOfPages);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * System.out.println("User Added Image Directory is "+ userAddedImagesDir);
	 * 
	 * return "adminDashboard"; }
	 */
	
	@GetMapping("/admin/pages")
	public String getPages(Model model) {
		model.addAttribute("pages", pageService.getAllPages());
//		String pageLink = 
//		HttpServletRequest request = ;
		
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

//	@ResponseBody
	@PostMapping("/admin/pages/add")
	public String pagesAddPost(@ModelAttribute("pageDTO") PageDTO pageDTO, HttpServletRequest request) {

		/*
		 * Create a new time stamp and initialize the timestamp with null
		 * Check if the entry in database is there for the date of creation...
		 * If it is not then initialise the time stamp with a new date.
		*/
		
		Page page = new Page();
		Date timeStamp = null;
		boolean creatingTimeStamp = false;
			
		if(pageDTO.getDateOfCreation() == null) {
			timeStamp= new Date();
			creatingTimeStamp = true;
		}

		if(creatingTimeStamp) {
			page.setDateOfCreation(timeStamp);
		}
		
		else {
			page.setDateOfCreation(pageDTO.getDateOfCreation());
		}
		
		page.setId(pageDTO.getId());
		page.setPageTitle(pageDTO.getPageTitle().strip());
		page.setBannerHeading(pageDTO.getBannerHeading());
		page.setBannerSubHeading(pageDTO.getBannerSubHeading());
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
		
		String preProcessFileName = pageDTO.getPageTitle().toLowerCase().strip();
//		preProcessFileName.toLowerCase();
		System.out.println("The Pre Process of file name "+preProcessFileName);
		
		String htmlFileName = preProcessFileName.replaceAll(" ","-");
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
		} 
		
		// Problem will occur when user will enter 3 the same name for more than 2 times...
		// Some code will have to be written to handle this problem using string and regex manipulation	
		// htmlFileName.
		
		page.setExtractedFileName(htmlFileName);

		htmlFileName += extention;
		String pagesLink = htmlFileName;
		try {
			Path fileNameAndPath = Paths.get(newPageAddDir, htmlFileName);
			Files.createFile(fileNameAndPath);
			
			

		} catch (IOException e) {
			// TODO: handle exception
		} 
		
		String hostName = request.getHeader("host");
		System.out.println(hostName);
		
		pagesLink = hostName + "/addedPages/";
		String currentPageLink = pagesLink + htmlFileName;
		
		/*
		 * Write a code to create a  page link.
		*/
		System.out.println(pagesLink);
			
		/*
		 * Now save the file name in the database so as to access the 
		 * file in the future while updating...
		 * Searching with the exact file name will be required.
		*/
		pageDTO.setFileName(htmlFileName);
		page.setFileName(pageDTO.getFileName());	

		/*
		 * Lets save the link of the file in the database
		*/
		pageDTO.setPageLink(currentPageLink);
		page.setPageLink(pageDTO.getPageLink());	
		pageService.addPage(page);
//		htmlPage
	
		/*
		 * Logic for adding the content in the file to be over here
		 * It the end publish the page...
		*/
		
		
		System.out.println("\n\n\n\n\n\n Main Section preview"+pageDTO.getMainSection());
		
		String codeInFile = htmlBoilerPlate(pageDTO.getMetaTitle(),
											pageDTO.getBannerHeading(), 
											pageDTO.getBannerSubHeading(),
											pageDTO.getMetaDescription(),
											pageDTO.getMainSection(),
											pageDTO.getCssCode());
		System.out.println("The following code will be there in the file "+codeInFile);
		pageDTO.setConsolidatedHTMLCode(codeInFile);
		page.setConsolidatedHTMLCode(pageDTO.getConsolidatedHTMLCode());
		
		try {
			pushCodeInFile(codeInFile, pageDTO.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		page.setLastModified(pageDTO.getLastModified());
		System.out.println("page Added sucessfully" + codeInFile);
		pageService.addPage(page);
//		String pageToReturn = "redirect:/viewPages/"+htmlFileName;
		System.out.println(page.toString());
		return "redirect:/admin/pages";

		
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
		String boilerPlate= " <!DOCTYPE html>\r\n"
				+ "<html lang=\"en\" xmlns:layout=\"http://www.ultraq.net.nz/thymeleaf/layout\"\r\n"
				+ "	layout:decorate=\"_LivePagelayout\">\r\n"
				+ "<head>\r\n"
//				+ header to be implemented later
				+ "</head>\r\n"
				+ ""
				
				+ "<body id=\"page-top\">\r\n"
				+ "\r\n"
				+ "	<!-- Content Wrapper -->\r\n"
				+ "	<div layout:fragment=\"contentPlus\">"
				+ mainSection
				+ "<footer class=\"footer_area f_bg\">\r\n"
				+ "        <div class=\"footer_cta fcta_bg\">\r\n"
				+ "           <div class=\"container\">\r\n"
				+ "                <div class=\"row\">\r\n"
				+ "                    <div class=\"col-lg-3 text-lg-left text-sm-center\">\r\n"
				+ "                        <img src=\"/viewPagesAssets/img/logo.png\">\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"col-lg-6 text-lg-center text-sm-center\">\r\n"
				+ "                            <h2 class=\"\">Enroll to transform your Lives</h2>\r\n"
				+ "                            <p class=\"f_size_22\"> Get a hassle free education loan is 3 days</p>\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"col-lg-3 text-lg-right text-sm-center\">\r\n"
				+ "                        <a href=\"\" class=\"btn_yellow \">Apply Now</a>\r\n"
				+ "                    </div>\r\n"
				+ "                </div>\r\n"
				+ "           </div>\r\n"
				+ "        </div>\r\n"
				+ "        <div class=\"footer_top\">\r\n"
				+ "            <div class=\"container\">\r\n"
				+ "                <div class=\"row\">\r\n"
				+ "                    \r\n"
				+ "                    <div class=\"col-lg-3 col-md-6\">\r\n"
				+ "                        <div class=\"f_widget about-widget  wow fadeInLeft\" data-wow-delay=\"0.4s\"\r\n"
				+ "                            style=\"visibility: visible; animation-delay: 0.4s; animation-name: fadeInLeft;\">\r\n"
				+ "                            <h3 class=\"f-title f_500 text-white f_size_18 mb_40\">Education Loan </h3>\r\n"
				+ "                            <ul class=\"list-unstyled f_list\">\r\n"
				+ "                                <li><a href=\"#\">Avanse Education Loans</a></li>\r\n"
				+ "                                <li><a href=\"#\">Study in India Education Loan</a></li>\r\n"
				+ "                                <li><a href=\"#\">Study Abroad Education Loan</a></li>\r\n"
				+ "                                <li><a href=\"#\">Executive Education Loan</a></li>\r\n"
				+ "                                <li><a href=\"#\">Student Loan Refinancing</a></li>\r\n"
				+ "                            </ul>\r\n"
				+ "                        </div>\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"col-lg-3 col-md-6\">\r\n"
				+ "                        <div class=\"f_widget about-widget  wow fadeInLeft\" data-wow-delay=\"0.4s\"\r\n"
				+ "                            style=\"visibility: visible; animation-delay: 0.4s; animation-name: fadeInLeft;\">\r\n"
				+ "                            <h3 class=\"f-title f_500 text-white f_size_18 mb_40\">Calculator</h3>\r\n"
				+ "                            <ul class=\"list-unstyled f_list\">\r\n"
				+ "                                <li><a href=\"#\">Course Expense Calculator</a></li>\r\n"
				+ "                                <li><a href=\"#\">Eligibility Calculator</a></li>\r\n"
				+ "                                <li><a href=\"#\">Eligibility Calculator</a></li>\r\n"
				+ "                                <li><a href=\"#\">Education Loan Repayment Calculator</a></li>\r\n"
				+ "                            </ul>\r\n"
				+ "                        </div>\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"col-lg-3 col-md-6\">\r\n"
				+ "                        <div class=\"f_widget about-widget  wow fadeInLeft\" data-wow-delay=\"0.6s\"\r\n"
				+ "                            style=\"visibility: visible; animation-delay: 0.6s; animation-name: fadeInLeft;\">\r\n"
				+ "                            <h3 class=\"f-title f_500 text-white f_size_18 mb_40\">Company</h3>\r\n"
				+ "                            <ul class=\"list-unstyled f_list\">\r\n"
				+ "                                    <li><a href=\"#\">About Avanse</a></li>\r\n"
				+ "                                    <li><a href=\"#\">Career</a></li>\r\n"
				+ "                                    <li><a href=\"#\">Investors</a></li>\r\n"
				+ "                                    <li><a href=\"#\">Media Room</a></li>\r\n"
				+ "                                    <li><a href=\"#\">Responsible Lending</a></li>\r\n"
				+ "                                    <li><a href=\"#\">Sitemap</a></li>\r\n"
				+ "                            </ul>\r\n"
				+ "                        </div>\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"col-lg-3 col-md-6\">\r\n"
				+ "                        <div class=\"f_widget about-widget  wow fadeInLeft\" data-wow-delay=\"0.8s\"\r\n"
				+ "                            style=\"visibility: visible; animation-delay: 0.8s; animation-name: fadeInLeft;\">\r\n"
				+ "                            <h3 class=\"f-title f_500 text-white f_size_18 mb_40\">Resources </h3>\r\n"
				+ "                            <ul class=\"list-unstyled f_list\">\r\n"
				+ "                                <li><a href=\"#\">Blog</a></li>\r\n"
				+ "                                <li><a href=\"#\">Good Credit</a></li>\r\n"
				+ "                                <li><a href=\"#\">FAQ</a></li>\r\n"
				+ "                                <li><a href=\"#\">Pay Online</a></li>\r\n"
				+ "                                <li><a href=\"#\">WhatsApp Communication</a></li>\r\n"
				+ "                                <li><a href=\"#\">Ex-gratia FAQs</a></li>\r\n"
				+ "                                <li><a href=\"#\">Sarfaesi Notice</a></li>\r\n"
				+ "                            </ul>\r\n"
				+ "                        </div>\r\n"
				+ "                    </div>\r\n"
				+ "                </div>\r\n"
				+ "            </div>\r\n"
				+ "        </div>\r\n"
				+ "        <div class=\"footer_bottom\">\r\n"
				+ "            <div class=\"container\">\r\n"
				+ "                <div class=\"row align-items-center\">\r\n"
				+ "                    <div class=\"col-lg-8 col-md-8 col-sm-12\">\r\n"
				+ "                        <p class=\"mb-2 f_300\">Copyright © 2021 Avanse Financial Services Ltd. All Rights Reserved .       <a class=\"ml-4\" href=\"https://www.digistreetmedia.com/\" target=\"_blank\">Site Credits</a></p>\r\n"
				+ "                        <p class=\"mb-2 f_300\">CIN : U67120MH1992PLC068060</p>\r\n"
				+ "                    </div>\r\n"
				+ "                    \r\n"
				+ "                    <div class=\"col-lg-4 col-md-4 col-sm-12\">\r\n"
				+ "                        <div class=\"f_social_icon_two text-right mb-2\">\r\n"
				+ "                            <a href=\"#\"><i class=\"ti-facebook\"></i></a>\r\n"
				+ "                            <a href=\"#\"><i class=\"ti-twitter-alt\"></i></a>\r\n"
				+ "                            <a href=\"#\"><i class=\"ti-linkedin\"></i></a>\r\n"
				+ "                            <a href=\"#\"><i class=\"ti-instagram\"></i></a>\r\n"
				+ "                        </div>\r\n"
				+ "                        <p class=\"mb-2 f_300 text-lg-right\">Phone support: 1800-266-0200</p>\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"col-lg-12\">\r\n"
				+ "                        <p>Disclaimer | Base Lending Rate | Privacy Policy | Terms & Conditions | Ombudsman Scheme | Customer Complaints | Moratorium Policy | WhatsApp T&C | Communication Policy | Digital Partners</p>\r\n"
				+ "                    </div>\r\n"
				+ "                </div>\r\n"
				+ "            </div>\r\n"
				+ "        </div>\r\n"
				+ "    </footer>\r\n"
				+ "    "
				
				
				+ "    <!-- Optional JavaScript -->\r\n"
				
				+ "    <!-- jQuery first, then Popper.js, then Bootstrap JS -->\r\n"
				+ "    <script src=\"/viewPagesAssets/js/jquery-3.6.0.min.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/js/propper.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/js/bootstrap.min.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/vendors/wow/wow.min.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/vendors/sckroller/jquery.parallax-scroll.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/vendors/owl-carousel/owl.carousel.min.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/vendors/imagesloaded/imagesloaded.pkgd.min.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssetsvendors/isotope/isotope-min.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/vendors/magnify-pop/jquery.magnific-popup.min.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/vendors/counterup/jquery.counterup.min.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/vendors/counterup/jquery.waypoints.min.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/vendors/counterup/appear.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/vendors/circle-progress/circle-progress.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/vendors/scroll/jquery.mCustomScrollbar.concat.min.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/js/plugins.js\"></script>\r\n"
				+ "    <script src=\"/viewPagesAssets/js/main.js\"></script>\r\n"
				+ " 	</body> ";
	
		return boilerPlate;
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
		pageDTO.setPageLink(page.getPageLink());
//		page.setPageLayout(pageDTO.getPageLayout());
		pageDTO.setMainSection(page.getMainSection());
		pageDTO.setBannerImageName(page.getBannerImageName());
		pageDTO.setBannerImageAlt(page.getBannerImageAlt());
		pageDTO.setCssCode(page.getCssCode());
		pageDTO.setFileName(page.getFileName());
		pageDTO.setJsCode(page.getJsCode());
		pageDTO.setMetaTitle(page.getMetaTitle());
		pageDTO.setMetaKeyword(page.getMetaKeyword());
		pageDTO.setMetaDescription(page.getMetaDescription());
		model.addAttribute("pageDTO", pageDTO);	
		return "pagesAdd";
	}
	
	@GetMapping("/admin/activateDeactivatePage/{id}/{action}")
	@ResponseBody
	@CrossOrigin("*")
	public String activateDeactivatePage(@PathVariable(name = "id") long id, @PathVariable String action) {
		System.out.println("Requested for Page action = " + action + " for Page id= " + id);
		Page page = pageService.getPageById(id).get();

		if (action.equals("ActivatePage")) {
			page.setIsPageActive(true);
			pageService.addPage(page);
			return "Page Activated/Published";			
			
		}

		else {
			page.setIsPageActive(false);
			pageService.addPage(page);
			return "Page Deactivate/Unpublished";
		}
	}
	
	/*
	 * Below Function is to access the global CSS file located at
	 * /viewPagesAssets/css/style.css"
	*/
	
	@GetMapping("/admin/globalHeader")
	public String globalHeaderAddGet(Model model) {
		model.addAttribute("header", new Header());
		return "globalHeader";
	}
	
//	@PostMapping("/admin/globalHeader/edit/{id}")
//	public String globalHeaderAddPost(Model model, @PathVariable long id) {
//		Header header = headerService.getHeaderById(id).get();
//		model.addAttribute("header",header);
//		System.out.println(header.toString());
//		return "redirect:/admin/globalHeader";
//	}
//	
	
	
	
	/*
	 * Below functions will be used to create the posts 
	*/
	
	@GetMapping("/admin/posts")
	public String getPosts(Model model) {
	model.addAttribute("posts", postService.getAllPosts());
		return "posts";
	}
	
	@GetMapping("/admin/posts/add")
	public String postsGet(Model model) {
		model.addAttribute("postDTO", new PostDTO());
		model.addAttribute("postCategories",postCategoryService.getAllPostCategories());
		return "postsAdd";
	}
	
	/*
	 * @PostMapping("/admin/posts/add") public String
	 * postAddPostMethod(@ModelAttribute("post") Post post) {
	 * postService.addPost(post); return "redirect:/admin/posts"; }
	 */
	
	@PostMapping("/admin/posts/add")
	public String blogPostsAddPostMap(@ModelAttribute("postDTO") PostDTO postDTO, @RequestParam("selectedCategories") String[] categoriesIds, HttpServletRequest request) {

		/*
		 * Create a new time stamp and initialize the timestamp with null
		 * Check if the entry in database is there for the date of creation...
		 * If it is not then initialise the time stamp with a new date.
		*/
		
		
		
		Post post= new Post();
		Date timeStamp = null;
		boolean creatingTimeStamp = false;
			
		if(postDTO.getDateOfCreation() == null) {
			timeStamp= new Date();
			creatingTimeStamp = true;
		}

		if(creatingTimeStamp) {
			post.setDateOfCreation(timeStamp);
		}
		
		else {
			post.setDateOfCreation(postDTO.getDateOfCreation());
		}
		
		post.setId(postDTO.getId());
		post.setPostTitle(postDTO.getPostTitle().strip());
		post.setHeading(postDTO.getHeading());
		post.setSubHeading(postDTO.getSubHeading());
		post.setMainSection(postDTO.getMainSection());
		post.setFeaturedImageName(postDTO.getFeaturedImageName());
		post.setFeaturedImageAltText(postDTO.getFeaturedImageAltText());
		post.setMetaTitle(postDTO.getMetaTitle());
		post.setMetaDescription(postDTO.getMetaDescription());
		
		/*
		 * for(String s:categoriesIds) { PostCategory postCategory =
		 * postCategoryRepository.getById(Long.valueOf(s));
		 * postCategory.getPostList().add(post);
		 * postCategoryRepository.save(postCategory); }
		 */

		
		
		
		/*
		 * Creating a new html template
		 */
		String extention = ".html";
		/*
		 * Before creating the html file, we have to ensure that two files do not get
		 */
		
		String preProcessFileName = postDTO.getPostTitle().toLowerCase().strip();
//		preProcessFileName.toLowerCase();
		System.out.println("The Pre Process of file name "+preProcessFileName);
		
		String htmlFileName = preProcessFileName.replaceAll(" ","-");
		List<Post> allPosts= postRepository.findAll();
		Iterator<Post> iterator = allPosts.iterator();
		
		/*
		 * Rename the file if the file with the same name already exist
		 */
		
		int count = 0;
		while (iterator.hasNext()) {
			Post postUnderEvaluation = iterator.next();
			if (postUnderEvaluation.getMetaTitle().equalsIgnoreCase(htmlFileName)) {
				htmlFileName += ++count;
			}
		} 
		
		// Problem will occur when user will enter 3 the same name for more than 2 times...
		// Some code will have to be written to handle this problem using string and regex manipulation	
		// htmlFileName.
		
		post.setExtractedFileName(htmlFileName);

		htmlFileName += extention;
		
		try {
			Path fileNameAndPath = Paths.get(newPostAddDir, htmlFileName);
			Files.createFile(fileNameAndPath);
			
			

		} catch (IOException e) {
			// TODO: handle exception
		} 
		
//////		String hostName = request.getHeader("host");
////		System.out.println(hostName);
////		
////		String postLink = hostName + "/addedPages/";
//		String currentPageLink = postLink + htmlFileName;
		
		/*
		 * Write a code to create a  page link.
		*/
//		System.out.println(postLink);
			
		/*
		 * Now save the file name in the database so as to access the 
		 * file in the future while updating...
		 * Searching with the exact file name will be required.
		*/
		postDTO.setFileName(htmlFileName);
		post.setFileName(postDTO.getFileName());	

		/*
		 * Lets save the link of the file in the database
		*/
//		postDTO.setPostLink(currentPageLink);
		postDTO.setPostLink(postDTO.getPostLink());	
//		postService.addPost(post);
//		htmlPage
	
		/*
		 * Logic for adding the content in the file to be over here
		 * It the end publish the page...
		*/
		
		
		System.out.println("\n\n\n\n\n\n Main Section preview"+postDTO.getMainSection());
		
		String codeInFile = htmlBlogLayout(postDTO.getMetaTitle(),
							postDTO.getHeading(), 
							postDTO.getSubHeading(),
							postDTO.getMetaDescription(),
							postDTO.getMainSection());
		System.out.println("The following code will be there in the file "+codeInFile);
		postDTO.setConsolidatedHTMLCode(codeInFile);
		post.setConsolidatedHTMLCode(postDTO.getConsolidatedHTMLCode());
		
		try {
			pushCodeInFile(codeInFile, postDTO.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		post.setLastModified(postDTO.getLastModified());
		System.out.println("page Added sucessfully" + codeInFile);
		postService.addPost(post);
//		String pageToReturn = "redirect:/viewPages/"+htmlFileName;
		System.out.println(post.toString());
		return "redirect:/admin/posts";
		
	}

	private void pushCodeInBlogFile(String codeInFile, String fileName) throws IOException {
		// TODO Auto-generated method stub
		Path fileNameAndPath = Paths.get(newPostAddDir, fileName);
		Files.write(fileNameAndPath, codeInFile.getBytes());
	}

	private String htmlBlogLayout(String metaTitle, String heading, String subheading, String metaDescription, String mainSection) {
		
		// TODO Auto-generated method stub
		/*
		 * initial code
		 * 
		*/
		
		String layoutCode=metaTitle+ "    " + heading+ "    " + subheading+ "    " + mainSection+ "    "  ;
					
		return layoutCode;
	}

	/*Below functions will be used to create the post categories	*/
	
	@GetMapping("/admin/postCategories")
	public String getCategories(Model model) {
		
		model.addAttribute("postCategories",postCategoryService.getAllPostCategories());
		return "postCategories";
		
	}
	
	@GetMapping("/admin/postCategories/add")
	public String getCatAdd(Model model) {
		
		model.addAttribute("postCategory", new PostCategory());
		return "categoriesAdd";
		
	}
	
	@PostMapping("/admin/postCategories/add")
	public String postCatAdd(@ModelAttribute("postCategory") PostCategory postCategory) {
		
		postCategoryService.addPostCategory(postCategory);
		return "redirect:/admin/postCategories";
	}	
	
//	Delete Method for deleting the categories by id
	@GetMapping("/admin/postCategory/delete/{id}")
	public String deleteCat(@PathVariable long id) {
		postCategoryService.removePostCategoryById(id);
		//		categoryService.removeCategoryById(id);
		return "redirect:/admin/postCategories";
	}
	
//	Update Method for updating the categories
	@GetMapping("/admin/postCategory/edit/{id}")
	public String updateCat(@PathVariable Long id, Model model) {
//		Optional<Category> category = categoryService.getCategoryById(id);
		Optional<PostCategory>postCategory = postCategoryService.getPostCategoryById(id);
		if(postCategory.isPresent()) {
			model.addAttribute("postCategory", postCategory.get());
			return "categoriesAdd";
		}	
		else
			return "404";
	}

	/*
	 * Image upload location for summernote
	*/
	public String sendImageUploadLocation() {
		return "To do";
	}
	
//	End of class
}
package com.avanse.springboot.controller.globalPages;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avanse.springboot.model.University;
import com.avanse.springboot.repository.UniversityRepository;
import com.avanse.springboot.service.UniversityService;

@Controller
@RequestMapping(value = {"/public/api/homePage"})
public class HomePageController {
	
	@Autowired
	UniversityRepository universityRepository;

	@Autowired
	UniversityService universityService;
	
	@ResponseBody
	@PostMapping(value = "/getUniversitesInfos",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUniversityInfo(
			@RequestBody String[] country) {
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+country[0]);
		List<University> filterList =universityService.getAllUniversity().stream()
		.filter(uni -> 
			uni.getLocation().toLowerCase().equals(country[0].toLowerCase())
			).collect(Collectors.toList());
		if(filterList==null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(filterList);
		}
		
	}
}

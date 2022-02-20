package com.avanse.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avanse.springboot.model.CustomUserDetail;
import com.avanse.springboot.model.Page;
import com.avanse.springboot.model.User;
import com.avanse.springboot.repository.PageRepository;

@Service
public class PageService {
	
	@Autowired
	PageRepository pageRepository;
	/*
	 * Return the list of all the pages in the server
	*/
	public List<Page> getAllPages(){
		return pageRepository.findAll();
	}
	/*
	 * Function to delete a page by Id
	*/
	@Transactional
	public void removePageById(long id) {
		pageRepository.deleteById(id);	
	}	
	/*
	 * Save the page object details into the database
	*/
	@Transactional
	public void addPage(Page page) {
		pageRepository.save(page);
	}
	/*
	 * Return a page on requesting an id
	*/
	public Optional<Page> getPageById(long id){
		return pageRepository.findById(id);
	}
	/*
	 * Returns the number of pages in the database
	*/
	public long numberOfPages() {
		return pageRepository.count();
	}
	/*
	 * Return the page by the page name
	*/	
	public Page findPageByFilename(String filename){
		return pageRepository.findPageByFileName(filename);
	}

	/*
	 * public Optional<Page> getPageByPageTitle(String pageTitle) { // TODO
	 * Auto-generated method stub return pageRepository.findPageBytitle(pageTitle);
	 * }
	 */	
	
	
	
	
	public List<String[]> getAllUrisForPages() {
		return pageRepository.getAllUrisForPages();
	}
	
	
	
	
}
package com.avanse.springboot.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avanse.springboot.model.Page;

public interface PageRepository extends JpaRepository<Page, Long> {
	
//	Optional<Page> findPageBytitle(String title);
	Page findPageByFileName(String fileName);
	
	@Query(value = "Select P.id,P.customUri FROM Page P")
	List<String[]> getAllUrisForPages();
	
}

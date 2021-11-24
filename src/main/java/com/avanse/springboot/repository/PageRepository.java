package com.avanse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.Page;

public interface PageRepository extends JpaRepository<Page, Long> {
	

}

package com.avanse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.University;

public interface UniversityRepository extends JpaRepository<University, Long> {

}

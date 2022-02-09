package com.avanse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avanse.springboot.model.TypeDetailsCourse;

@Repository
public interface TypeDetailsCourseRepository extends JpaRepository<TypeDetailsCourse, Long>{

}

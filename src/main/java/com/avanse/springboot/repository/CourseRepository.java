package com.avanse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>  {

}

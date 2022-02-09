package com.avanse.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.avanse.springboot.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	List<Course> findAllByUniversity_Id(long id);
}

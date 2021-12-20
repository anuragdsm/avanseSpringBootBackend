package com.avanse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.PostCategory;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {

}

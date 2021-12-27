package com.avanse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

}

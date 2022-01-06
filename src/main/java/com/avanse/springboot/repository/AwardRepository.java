package com.avanse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.Award;

public interface AwardRepository extends JpaRepository<Award, Long>{

}

package com.avanse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.Header;

public interface HeaderRepository extends JpaRepository<Header, Long> {

}

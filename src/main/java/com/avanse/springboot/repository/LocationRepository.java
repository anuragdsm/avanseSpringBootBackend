package com.avanse.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

	Location findLocationByCity(String city);
}

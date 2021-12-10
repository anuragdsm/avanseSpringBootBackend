package com.avanse.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avanse.springboot.model.Location;
import com.avanse.springboot.repository.LocationRepository;

@Service
public class LocationService{
	
	@Autowired
	LocationRepository locationRepository;
	
//Return the list of all the locations 
	public List<Location> getAllLocations(){
		return locationRepository.findAll();
	}
	
	
//	Function to save the location into the database
	
	@Transactional
	public void addLocation(Location location) {
		locationRepository.save(location);
	}
	

	
}

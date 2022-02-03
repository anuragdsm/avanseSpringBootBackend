package com.avanse.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.Image;
import com.avanse.springboot.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	ImageRepository imageRepository;
	
	public List<Image> getAllImages(){
		return imageRepository.findAll();
	}
	
	public void removeImageById(long id) {
	
		imageRepository.deleteById(id);
	}
	
	public synchronized void addImage(Image image) {
		imageRepository.save(image);
	}
	
	public Optional<Image> getImagebyId(long id){
		return imageRepository.findById(id);
	}
}

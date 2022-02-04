package com.avanse.springboot.service.forms.contactUs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avanse.springboot.model.forms.contactUs.Media;
import com.avanse.springboot.repository.forms.contactUs.MediaRepository;

@Service
public class MediaService {

	@Autowired
	MediaRepository mediaRepository;
	
	public static final int MEDIA_LEADS_PER_PAGE= 4;

	public List<Media> getAllMediaLeads(){
		return mediaRepository.findAll();
	}
	
	public Page<Media> listMediaLeadsByPage(int pageNum){
		Pageable pageable = PageRequest.of(pageNum - 1 , MEDIA_LEADS_PER_PAGE);
		return mediaRepository.findAll(pageable);
	}
	
	public void addMedia(Media media) {
		mediaRepository.save(media);
	}
	
	public Optional<Media> getMediaById(long id){
		return mediaRepository.findById(id);
	}
	
	
	public void deleteMedia(long id) {
		mediaRepository.deleteById(id);
	}
	
	
	public long numberOfMediaLeads() {
		return mediaRepository.count();
	}
	
	
	
	
	
	
	
}

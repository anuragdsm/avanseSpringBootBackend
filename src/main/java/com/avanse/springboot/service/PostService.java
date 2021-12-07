package com.avanse.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avanse.springboot.model.Post;
import com.avanse.springboot.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	
	/*
	 * Return the list of all the posts in the server
	*/
	
	public List<Post> getAllPosts(){
		return postRepository.findAll();
	}
	
	/*
	 * Function to delete a post by Id
	*/
	
	@Transactional
	public void removePostById(long id) {
		postRepository.deleteById(id);	
	}
	
	/*
	 * Save the post object details into the database
	*/
	
	@Transactional
	public void addPost(Post post) {
		postRepository.save(post);
		
	}
	
	/*
	 * Return a post on requesting an id
	*/
	public Optional<Post> getPostById(long id){
		
		return postRepository.findById(id);
		
	}
	
	/*
	 * Returns the number of posts in the database
	*/
	public long numberOfPosts() {
		return postRepository.count();
	}
	
}

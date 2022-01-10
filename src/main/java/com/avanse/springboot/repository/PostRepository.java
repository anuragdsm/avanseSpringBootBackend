package com.avanse.springboot.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avanse.springboot.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

//	Optional<Page> findPageBytitle(String title);
	Post findPostByFileName(String fileName);
	
	
	Optional<List<Post>> findTop4ByOrderByDateOfCreationDesc();
	Optional<Post> findFirstByOrderByIdAsc();
	Optional<Post> findFirstByOrderByIdDesc();


}

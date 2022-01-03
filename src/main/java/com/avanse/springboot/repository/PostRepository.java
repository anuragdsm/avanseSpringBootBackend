package com.avanse.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.avanse.springboot.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

//	Optional<Page> findPageBytitle(String title);
	Post findPostByFileName(String fileName);


}

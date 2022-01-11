package com.avanse.springboot.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avanse.springboot.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

//	Optional<Page> findPageBytitle(String title);
	Post findPostByFileName(String fileName);
	
	
	Optional<List<Post>> findTop4ByOrderByDateOfCreationDesc();
	Optional<Post> findFirstByOrderByIdAsc();
	Optional<Post> findFirstByOrderByIdDesc();
	
	@Query(value = "select * from posts order by date_of_creation desc limit :lowerLimit , :upperLimit", nativeQuery = true)
	Optional<List<Post>> getPostsInRange(@Param("lowerLimit") long lowerLimit,@Param("upperLimit") long upperLimit);


}

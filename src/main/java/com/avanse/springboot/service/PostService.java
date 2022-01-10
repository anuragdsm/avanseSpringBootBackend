package com.avanse.springboot.service;

import java.util.ArrayList;
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

	public List<Post> getAllPosts() {
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
	public Optional<Post> getPostById(long id) {

		return postRepository.findById(id);

	}

	/*
	 * Returns the number of posts in the database
	 */
	public long numberOfPosts() {
		return postRepository.count();
	}

	/*
	 * Return the post by the post name
	 */
	public Post findPostByFilename(String filename) {
		return postRepository.findPostByFileName(filename);
	}

	public List<Post> getTopFourLatestPosts() {
		return postRepository.findTop4ByOrderByDateOfCreationDesc().get();
	}

	public List<Post> randomThreePosts() {
		List<Post> threePostList = new ArrayList<Post>();
		long postHighestIdInDb = postRepository.findFirstByOrderByIdDesc().get().getId();
		System.out.println("postHighestInDP = "+postHighestIdInDb);
		long postLowestIdInDb = postRepository.findFirstByOrderByIdAsc().get().getId();
		System.out.println("postLowestInDB = "+postLowestIdInDb);
		System.out.println("PostRepositoryCount " + postRepository.count());
		long noOfRandomPosts = 3 > postRepository.count() ? postRepository.count() : (3);
		System.out.println("NoOfRandomPosts : "+ noOfRandomPosts);
		for (int i = 0; i < noOfRandomPosts; i++) {
			Boolean check = true;
			if (noOfRandomPosts > 3) {
				while (check) {
					long tempId = (long) (Math.random() * (postHighestIdInDb - postLowestIdInDb) + postLowestIdInDb);
					System.out.println("Post at position "+i+"is "+ tempId );
					if (postRepository.findById(tempId).isPresent()
							&& randomPostsDuplicationCheck(tempId, threePostList)) {
						check = false;
						threePostList.add(postRepository.findById(tempId).get());
					}
				}
			} else {
				return postRepository.findAll();
			}
		}
		return threePostList;
	}

	public static Boolean randomPostsDuplicationCheck(long id, List<Post> threePostList) {
		if (threePostList.size() == 0)
			return true;
		else {
			int check = 0;
			for (Post p : threePostList) {
				if (p.getId() == id)
					break;
				else {
					check++;
					if (check == threePostList.size())
						return true;
				}
			}
			return false;
		}
	}

}

package com.project.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blog.entites.Category;
import com.project.blog.entites.Post;
import com.project.blog.entites.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	//getting all post by user
	List<Post> findByUser(User user);
	
	//getting all post by category
	List<Post> findByCategory(Category category);
	
	
	List<Post> findByTitleContaining(String title);

}

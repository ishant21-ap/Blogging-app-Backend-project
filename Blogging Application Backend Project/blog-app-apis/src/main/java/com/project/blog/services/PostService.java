package com.project.blog.services;

import java.util.List;

import com.project.blog.entites.Post;
import com.project.blog.payloads.PostDto;
import com.project.blog.payloads.PostResponse;

public interface PostService {

	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	PostDto getPostById(Integer postId);
	
	//get all post by category 
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all post by user
	List<PostDto> getPostByUser(Integer userId);
	
	
	//search posts
	List<PostDto> searchPost(String keyword);
}

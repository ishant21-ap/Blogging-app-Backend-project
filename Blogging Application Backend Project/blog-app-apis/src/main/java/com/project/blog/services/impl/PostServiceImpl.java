package com.project.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.blog.entites.Category;
import com.project.blog.entites.Post;
import com.project.blog.entites.User;
import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.payloads.PostDto;
import com.project.blog.payloads.PostResponse;
import com.project.blog.repositories.CategoryRepo;
import com.project.blog.repositories.PostRepo;
import com.project.blog.repositories.UserRepo;
import com.project.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		Post post = this.modelMapper.map(postDto, Post.class);   //We only get two things title and content, rest we need to set it manually
		
		//fetching user
		User user = this.userRepo.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException("User", "User id", userId));
		
		//fetching category
		Category category = this.categoryRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		
		
		//setting post image default, date
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		
		//setting user and category in our post 
		post.setUser(user);
		post.setCategory(category);
		
		
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	
	
	
	
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post posts = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));
		posts.setTitle(postDto.getTitle());
		posts.setContent(postDto.getContent());
		posts.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepo.save(posts);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}
	
	
	
	

	@Override
	public void deletePost(Integer postId) {

		Post posts = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "post id", postId));
		this.postRepo.delete(posts);
	}
	
	
	

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {
		
		//Pagination
		
		//hardcoded value
//		int pageSize = 5;
//		int pageNumber = 1;
		
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		//or
//		if(sortDir.equalsIgnoreCase("asc")) {
//			sort = Sort.by(sortBy).ascending();
//		}
//		else {
//			sort = Sort.by(sortBy).descending();
//		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePosts = this.postRepo.findAll(p);
		List<Post> allPosts = pagePosts.getContent();
		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());
		return postResponse;
	}
	
	
	

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}
	
	

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts  = this.postRepo.findByCategory(cat);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}
	
	
	

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	
	
	
	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}

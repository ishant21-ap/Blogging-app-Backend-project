package com.project.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.project.blog.entites.Category;
//import com.project.blog.entites.Comment;
import com.project.blog.entites.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;

	private String title;
	private String content;
	
	private String imageName;
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
//	private Set<CommentDto> comments = new HashSet<>();    //Now when we will fetch post we will get comments also
}

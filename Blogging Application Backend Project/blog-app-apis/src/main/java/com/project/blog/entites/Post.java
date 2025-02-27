package com.project.blog.entites;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(length = 10000, nullable = false)
	private String content;
	
	private String imageName;
	
	private Date addedDate;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	private User user;
	
	
	
//	
//	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//	private Set<Comment> comments = new HashSet<>();
}

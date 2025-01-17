package com.project.blog.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blog.entites.TemporaryUser;

public interface TempororyUserRepositories extends JpaRepository<TemporaryUser, String>{
	
	Optional<TemporaryUser> findByEmail(String email);

}

package com.project.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blog.entites.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	public Optional<User> findByEmail(String email);
}

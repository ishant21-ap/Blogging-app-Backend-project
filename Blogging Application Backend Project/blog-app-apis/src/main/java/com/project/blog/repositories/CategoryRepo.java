package com.project.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blog.entites.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}

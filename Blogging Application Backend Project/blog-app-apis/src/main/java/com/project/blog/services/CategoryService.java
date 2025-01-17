package com.project.blog.services;

import java.util.List;

import com.project.blog.payloads.CategoryDto;

public interface CategoryService {

	
	//In interface methods automatically become public and abstract so no need to write the public
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
    void deleteCategory(Integer categoryId);
	
	CategoryDto getCategory(Integer categoryId);
	
	List<CategoryDto> getAllCategories();
}

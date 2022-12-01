package com.bista.productservice.services;

import java.util.List;

import com.bista.productservice.entities.Category;

public interface CategoryService {

	Category addCategory(Category category);
	Category getCategory(Long id);
	List<Category> getAllCategories();
	Category updateCategory(Category category, Long id);
	void deleteCategory(Long id);
}

package com.bista.productservice.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bista.productservice.entities.Category;
import com.bista.productservice.exceptions.ResourceNotFoundException;
import com.bista.productservice.repositories.CategoryReposite;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryReposite categoryReposite;
	
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(CategoryService.class);
	
	@Override
	public Category addCategory(Category category) {
		 
		return categoryReposite.save(category);
	}

	@Override
	public Category getCategory(Long id) {
		if(!categoryReposite.existsById(id)) {
			logger.error("Error on Get Category: Category with id " + id + " not found");
			throw new ResourceNotFoundException( HttpStatus.NOT_FOUND, "Category not found");
		}
		return categoryReposite.findById(id).get();
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryReposite.findAll();
	}

	@Override
	public Category updateCategory(Category category, Long id) {
		
		if(!categoryReposite.existsById(id)) {
			logger.error("Error on Update Category: Category with id " + id + " not found");
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Category not found");
		}
		
		Category cat = categoryReposite.findById(id).get();
		
		category.setCategoryId(cat.getCategoryId());
		
		if(category.getName().equals("") || category.getName() == null) {
			category.setName(cat.getName());
		}
		
		return categoryReposite.save(category);
	}

	@Override
	public void deleteCategory(Long id) {
		if(!categoryReposite.existsById(id)) {
			logger.error("Error on Delete Category: Category with id " + id + " not found");
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Category not found.");
		}
		
		categoryReposite.deleteById(id);
		
	}

}

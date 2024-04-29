package com.bista.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bista.productservice.entities.Category;
import com.bista.productservice.services.CategoryService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category){
		
		Category cat = categoryService.addCategory(category);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cat);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable Long id){
		Category category = categoryService.getCategory(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(category);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> categories = categoryService.getAllCategories();
		
		return ResponseEntity.status(HttpStatus.OK).body(categories);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category, @PathVariable("id") Long id){
		Category cat = categoryService.updateCategory(category, id);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cat);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id){
		categoryService.deleteCategory(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully.");
	}
	
	
}

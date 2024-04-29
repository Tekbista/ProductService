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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bista.productservice.dto.PageableProduct;
import com.bista.productservice.entities.Product;
import com.bista.productservice.services.ProductService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
		Product prod = productService.addProduct(product);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(prod);
	}
	
	@PostMapping("/multiple")
	public ResponseEntity<List<Product>> addProducts(@Valid @RequestBody List<Product> products){
		List<Product> prods = productService.addProducts(products);
		return ResponseEntity.status(HttpStatus.CREATED).body(prods);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
			Product product = productService.getProduct(id);
			return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(){
		
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product prod, @PathVariable("id") Long id){
		Product product = productService.updateProduct(prod, id);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
		productService.deleteProduct(id);
		return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
	}
	
	
	@GetMapping("/category/{name}")
	public ResponseEntity<PageableProduct> getProductsByPage(@PathVariable("name")String category, @RequestParam("page") Integer page, @RequestParam("size") Integer size){
		PageableProduct pageableProduct= productService.getProductByPage(category, page, size);
		
		return ResponseEntity.status(HttpStatus.OK).body(pageableProduct);
	}
	
	@GetMapping("/newarrival")
	public ResponseEntity<List<Product>> getNewArrivalProducts(){
		List<Product> newArrivals = productService.getNewArrivalProducts();
		return ResponseEntity.status(HttpStatus.OK).body(newArrivals);
	}
	
	@GetMapping("/onsale")
	public ResponseEntity<List<Product>> getProductOnSale(){
		List<Product> onSale = productService.getProductOnSale();
		return ResponseEntity.status(HttpStatus.OK).body(onSale);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<PageableProduct> searchProduct(@PathVariable("keyword") String keyword, @RequestParam("page") Integer page, @RequestParam("size") Integer size){
		PageableProduct searchResult = productService.searchProduct(keyword, page, size);
		
		return ResponseEntity.status(HttpStatus.OK).body(searchResult);
	}
	
}

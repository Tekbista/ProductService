package com.bista.productservice.services;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bista.productservice.dto.PageableProduct;
import com.bista.productservice.entities.Product;
import com.bista.productservice.exceptions.ResourceNotFoundException;
import com.bista.productservice.repositories.ProductRepository;




@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Override
	public Product addProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public Product getProduct(Long id) {
		if(!productRepository.existsById(id)) {
			logger.error("Product with id " + id + " not found." );
			throw new ResourceNotFoundException( HttpStatus.NOT_FOUND, "Product not found." );
		}
		
		return productRepository.findById(id).get();
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products;
	}
	

	@Override
	public void deleteProduct(Long id) {
		
		if(!productRepository.existsById(id)) {
			logger.error("Product with id " + id + " not found." );
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Product not found.");
		}
		
		productRepository.deleteById(id);
		
	}

	@Override
	public Product updateProduct(Product product, Long id) {
		Product dbProduct = new Product();
		
		if(productRepository.findById(id).isPresent()) {
			dbProduct = productRepository.findById(id).get();
		}else {
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Product not found.");
		}
		
		product.setProductId(dbProduct.getProductId());
		
		if(product.getProductName() == "" || product.getProductName() == null) {
			product.setProductName(dbProduct.getProductName());
		}
		
		if(product.getCategory() == null ) {
			product.setCategory(dbProduct.getCategory());
		}
		
		if(product.getPrice() == null){
			product.setPrice(dbProduct.getPrice());
		}
		
		if(product.getDiscountPrice() == null) {
			product.setDiscountPrice(dbProduct.getDiscountPrice());
		}
		
		if(product.getDescription().equals("") || product.getDescription() == null) {
			product.setDescription(dbProduct.getDescription());
		}
		
		if(product.getCreatedOn() == null) {
			product.setCreatedOn(dbProduct.getCreatedOn());
		}
		
		if(product.getImage().equals("") || product.getImage() == null) {
			product.setImage(dbProduct.getImage());
		}
		
		
		return productRepository.save(product);
	}

	@Override
	public PageableProduct getProductByPage(String category, Integer pageNum, Integer pageSize) {
		PageableProduct pageableProduct = new PageableProduct();
		List<Product> products = new ArrayList<>();
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		
		Page<Product> page = productRepository.findByCategoryName(category, pageable);
		
		if(page.hasContent()) {
            products = page.getContent();
        } 
		
		pageableProduct.setProducts(products);
		pageableProduct.setTotalPage(page.getTotalPages());
		
		return pageableProduct;
		
	}


	@Override
	public List<Product> getProductOnSale() {
		return productRepository.findProductOnSale();
	}

	@Override
	public List<Product> getNewArrivalProducts() {
		return productRepository.findTop8ByOrderByCreatedOnDesc();
	}

	@Override
	public List<Product> addProducts(List<Product> products) {
		if(products.size() > 0) return productRepository.saveAll(products);
		return null;
	}

	@Override
	public PageableProduct searchProduct(String keyword, Integer pageNum, Integer pageSize) {
		PageableProduct pageableProduct = new PageableProduct();
		List<Product> products = new ArrayList<>();
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		
		Page<Product> page = productRepository.findByDescriptionContainingIgnoreCase(keyword, pageable);
		
		if(page.hasContent()) {
            products = page.getContent();
        } 
		
		pageableProduct.setProducts(products);
		pageableProduct.setTotalPage(page.getTotalPages());
		
		return pageableProduct;
	}


}

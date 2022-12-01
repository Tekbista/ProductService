package com.bista.productservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bista.productservice.dto.PageableProduct;
import com.bista.productservice.entities.Product;

@Service
public interface ProductService {

	Product addProduct(Product product);
	Product getProduct(Long id);
	Product updateProduct(Product product, Long id);
	List<Product> getAllProducts();
	void deleteProduct(Long id);
	PageableProduct getProductByPage(String category, Integer pageNum, Integer pageSize);
	List<Product> getNewArrivalProducts();
	List<Product> getProductOnSale();
}

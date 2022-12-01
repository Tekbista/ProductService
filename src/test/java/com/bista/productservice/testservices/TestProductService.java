package com.bista.productservice.testservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bista.productservice.entities.Category;
import com.bista.productservice.entities.Product;
import com.bista.productservice.repositories.ProductRepository;
import com.bista.productservice.services.ProductService;
import com.bista.productservice.services.ProductServiceImpl;

@SpringBootTest(classes = {ProductService.class})
public class TestProductService {

	@Mock
	ProductRepository productRepository;
	
	@InjectMocks
	ProductServiceImpl productService;
	
	
	
	@Test
	@Order(1)
	public void testAddProduct() {
		
		Product product = new Product(1L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 0.0, "images", "Description", LocalDate.now(), false);
		
		
		when(productRepository.save(product)).thenReturn(product);
		assertEquals("MacBook", productService.addProduct(product).getProductName());
		
		
	}
	
	@Test
	@Order(2)
	public void testGetAllProducts() {
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product(1L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 0.0, "images", "Description", LocalDate.now(), false));
		products.add(new Product(2L, "Acer", new Category(1L, "Laptops", null), 1000.0, 50.0, "images", "Description", LocalDate.now(), false));
		
		when(productRepository.findAll()).thenReturn(products);
		assertEquals(2, productService.getAllProducts().size());
	}
	
	@Test
	@Order(3)
	public void testGetNewArrivalProducts() {
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product(1L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 0.0, "images", "Description", LocalDate.now(), false));
		products.add(new Product(2L, "Acer", new Category(1L, "Laptops", null), 1000.0, 50.0, "images", "Description", LocalDate.now(), false));
		
		when(productRepository.findTop8ByOrderByCreatedOnDesc()).thenReturn(products);
		assertEquals(2, productService.getNewArrivalProducts().size());
	}
	
	@Test
	@Order(4)
	public void testGetProductOnSale() {
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product(1L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 45.0, "images", "Description", LocalDate.now(), false));
		products.add(new Product(2L, "Acer", new Category(1L, "Laptops", null), 1000.0, 50.0, "images", "Description", LocalDate.now(), false));
		
		when(productRepository.findProductOnSale()).thenReturn(products);
		assertTrue(productService.getProductOnSale().get(0).getDiscountPrice() > 0.0);
		assertTrue(productService.getProductOnSale().get(1).getDiscountPrice() > 0.0);
	}
	
	
	@Test @Order(5)
	public void testGetProduct() {
		Long productId = 1L;
		Product product = new Product(1L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 0.0, "images", "Description", LocalDate.now(), false);
		when(productRepository.existsById(productId)).thenReturn(true);
		when(productRepository.findById(productId).get()).thenReturn(product);
		assertEquals("MacBook", productService.getProduct(productId).getProductName());
	}
	
	
}

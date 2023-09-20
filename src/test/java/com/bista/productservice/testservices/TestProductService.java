package com.bista.productservice.testservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bista.productservice.entities.Category;
import com.bista.productservice.entities.Product;
import com.bista.productservice.exceptions.ResourceNotFoundException;
import com.bista.productservice.repositories.ProductRepository;
import com.bista.productservice.services.ProductService;
import com.bista.productservice.services.ProductServiceImpl;

@SpringBootTest(classes = {ProductService.class})
public class TestProductService {

	@Mock
	ProductRepository productRepository;
	
	@InjectMocks
	ProductServiceImpl productService;
	
	
	

//	@Disabled
	
	@Test
	public void testAddProduct() {
		Product product = new Product(1L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 0.0, new ArrayList<>(), "Description", LocalDate.now(), false, 1);
		when(productRepository.save(product)).thenReturn(product);
		assertEquals("MacBook", productService.addProduct(product).getProductName());	
	}
	
	@Test
	public void testGetProduct() {
		Long productId = 1L;
		Optional<Product> product = Optional.of(new Product(1L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 0.0, new ArrayList<>(), "Description", LocalDate.now(), false, 1));
		when(productRepository.existsById(productId)).thenReturn(true);
		when(productRepository.findById(productId)).thenReturn(product);
		assertEquals("MacBook", productService.getProduct(productId).getProductName());
		assertEquals(1, productService.getProduct(productId).getProductId());
	}
	
	@Test 
	public void testGetProductThrowException() {
		Long productId = 1L;
		when(productRepository.existsById(productId)).thenReturn(false);
		assertThrows(ResourceNotFoundException.class, () -> {
			productService.getProduct(productId);
		});
	}
	
	
//	@Disabled
	@Test
	public void testGetAllProducts() {
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product(1L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 0.0, new ArrayList<>(), "Description", LocalDate.now(), false, 1));
		products.add(new Product(2L, "Acer", new Category(1L, "Laptops", null), 1000.0, 50.0, new ArrayList<>(), "Description", LocalDate.now(), false, 1));
		
		when(productRepository.findAll()).thenReturn(products);
		assertEquals(2, productService.getAllProducts().size());
	}
//	@Disabled
	@Test 
	public void testDeleteProduct() {
		Long productId = 1L;
		when(productRepository.existsById(productId)).thenReturn(true);
		doNothing().when(productRepository).deleteById(productId);
		productService.deleteProduct(productId);
	}
	
	@Test 
	public void testDeleteProductThrowException() {
		Long productId = 1L;
		when(productRepository.existsById(productId)).thenReturn(false);
		assertThrows(ResourceNotFoundException.class, () -> {
			productService.deleteProduct(productId);
		});
		
	}
	
	@Test
	public void testGetNewArrivalProducts() {
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product(1L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 0.0, new ArrayList<>(), "Description", LocalDate.now(), false, 1));
		products.add(new Product(2L, "Acer", new Category(1L, "Laptops", null), 1000.0, 50.0, new ArrayList<>(), "Description", LocalDate.now(), false, 1));
		
		when(productRepository.findTop8ByOrderByCreatedOnDesc()).thenReturn(products);
		assertEquals(2, productService.getNewArrivalProducts().size());
	}
	

//	@Disabled
	@Test
	public void testGetProductOnSale() {
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product(1L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 45.0, new ArrayList<>(), "Description", LocalDate.now(), false, 1));
		products.add(new Product(2L, "Acer", new Category(1L, "Laptops", null), 1000.0, 50.0, new ArrayList<>(), "Description", LocalDate.now(), false, 1));
		
		when(productRepository.findProductOnSale()).thenReturn(products);
		assertTrue(productService.getProductOnSale().get(0).getDiscountPrice() > 0.0);
		assertTrue(productService.getProductOnSale().get(1).getDiscountPrice() > 0.0);
	}
	
	
	@Test
	public void testUpdateProduct() {
		Long productId = 1L;
		Product product = new Product(1L, "", new Category(1L, "", null), 2000.0, 0.0, new ArrayList<>(), "", LocalDate.now(), false, 1);
		Product updatedProduct = new Product(1L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 0.0, new ArrayList<>(), "Description", LocalDate.now(), false, 1);
		Optional<Product> bdProduct = Optional.of(new Product(1L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 0.0, new ArrayList<>(), "Description", LocalDate.now(), false, 1));
		when(productRepository.findById(productId)).thenReturn(bdProduct);
		when(productRepository.save(product)).thenReturn(updatedProduct);
		assertEquals(new ArrayList<>(), productService.updateProduct(product, productId).getImage());
	}
	
	@Test
	public void testUpdateProductThrowException() {
		Long productId = 1L;
		Product product = new Product(1L, "", new Category(1L, "", null), 2000.0, 0.0, new ArrayList<>(), "", LocalDate.now(), false, 1);
		when(productRepository.findById(productId)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> {
			productService.updateProduct(product, productId);
		});
	}

	@Test
	public void testGetProductByPage() {
		int pageNumber = 0;
		int numOfItemOnPageLong = 2;
		Pageable pageable = PageRequest.of(pageNumber, numOfItemOnPageLong);
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product(1L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 45.0, new ArrayList<>(), "Description", LocalDate.now(), false, 1));
		products.add(new Product(2L, "Acer", new Category(1L, "Laptops", null), 1000.0, 50.0, new ArrayList<>(), "Description", LocalDate.now(), false, 1));
		//products.add(new Product(3L, "MacBook", new Category(1L, "Laptops", null), 2000.0, 45.0, "images", "Description", LocalDate.now(), false));
		//products.add(new Product(4L, "Acer", new Category(1L, "Laptops", null), 1000.0, 50.0, "images", "Description", LocalDate.now(), false));
		
		Page<Product> page = new PageImpl<>(products, pageable, products.size());
		when(productRepository.findByCategoryName("Laptops", pageable)).thenReturn(page);
		
		assertEquals(2, productService.getProductByPage("Laptops", pageNumber, numOfItemOnPageLong).getProducts().size());
		assertEquals(1, productService.getProductByPage("Laptops", pageNumber, numOfItemOnPageLong).getTotalPage());
	}
	
}

package com.bista.productservice.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bista.productservice.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query(value = "SELECT * from product ORDER By date_created DESC LIMIT 3", nativeQuery = true)
	List<Product> findThreeRecentBanner();

	Page<Product> findByCategoryName(String category, Pageable pageable);
	List<Product> findTop8ByOrderByCreatedOnDesc();
	@Query(value = "SELECT * from product WHERE discount_price > 0 LIMIT 8", nativeQuery = true)
	List<Product> findProductOnSale();
	Page<Product> findByDescriptionContainingIgnoreCase(String keyword, Pageable pageable);
}

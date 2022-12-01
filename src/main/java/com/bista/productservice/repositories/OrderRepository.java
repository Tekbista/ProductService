package com.bista.productservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bista.productservice.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByUserId(Long userId);
	boolean existsByUserId(Long id);
}

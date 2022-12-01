package com.bista.productservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bista.productservice.entities.Order;

@Service
public interface OrderService {

	Order addOrder(Order order);
	List<Order> getUserOrders(Long userId);
	Order getOrder(Long id);
	List<Order> getAllOrders();
	Order updateOrderStatus(Long id, String status);
}

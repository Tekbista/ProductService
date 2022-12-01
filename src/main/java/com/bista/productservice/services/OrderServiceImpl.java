package com.bista.productservice.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.bista.productservice.entities.Order;
import com.bista.productservice.exceptions.ResourceNotFoundException;
import com.bista.productservice.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	
	@Override
	public Order addOrder(Order order) {
		
		return orderRepository.save(order);
	}

	

	@Override
	public List<Order> getUserOrders(Long userId) {
		
		if(!orderRepository.existsByUserId(userId)) {
			logger.error("Error User Order: Order not found for userId: " + userId);
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "No order found.");
		}
		
		List<Order> orders = orderRepository.findByUserId(userId);
		
		return orders;
	}

	@Override
	public Order getOrder(Long id) {
		
		if(!orderRepository.existsById(id)) {
			logger.error("Error Get Order: Order not found with orderId: " + id);
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Order not found.");
		}
		Order order = orderRepository.findById(id).get();
		return order;
	}

	@Override
	public List<Order> getAllOrders() {
		
		return orderRepository.findAll();
	}
	
	
	@Override
	public Order updateOrderStatus( Long id, String status) {
		if(!orderRepository.existsById(id)) {
			logger.error("Error Update Order Status: Order not found with orderId: " + id);
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Order not found");
		}
		
		Order dbOrder = orderRepository.findById(id).get();
		
		dbOrder.setStatus(status);
		return orderRepository.save(dbOrder);
	}

}

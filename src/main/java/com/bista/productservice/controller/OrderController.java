package com.bista.productservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bista.productservice.entities.Order;
import com.bista.productservice.services.OrderService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	@PostMapping()
	public ResponseEntity<String> placeOrder(@Valid @RequestBody Order order){
		Long orderId = orderService.addOrder(order).getOrderId();
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Your order has been placed. Here is your order number: " + orderId);
	}
	
	@GetMapping("users/{id}")
	public ResponseEntity<List<Order>> getUserOrders(@PathVariable("id") Long userId){
		
		List<Order> orders = orderService.getUserOrders(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") Long id){
		Order order = orderService.getOrder(id);
		 
		return ResponseEntity.status(HttpStatus.OK).body(order);
	}
	
	@GetMapping()
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> orders = orderService.getAllOrders();
		
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable("id") Long id, @RequestParam("status") String status){
		Order order = orderService.updateOrderStatus(id, status);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}
	
	
}

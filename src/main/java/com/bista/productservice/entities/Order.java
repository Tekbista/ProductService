package com.bista.productservice.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	@NotNull(message = "User not identified.")
	private Long userId;
	@NotEmpty
	@Size(min = 1, max  = 100)
	private String status;
	private LocalDateTime orderPlacedOn;
	private boolean isDelivered;
	private LocalDateTime orderDeliveredOn;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "orders_products", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
	private List<Product> products;

	

}

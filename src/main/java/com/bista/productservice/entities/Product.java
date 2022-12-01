package com.bista.productservice.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;

	@Column(name = "product_name")
	@NotEmpty(message = "Product name cannot be empty.")
	@Size(max = 100, message = "Product name must be less then 100 characters.")
	private String productName;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
	

	@Column(name = "price", nullable = false)
	@Min(value = 0, message = "Price cannot be less than 0.")
	private Double price;

	@Column(name = "discount_price", nullable = false)
	private Double discountPrice;

	@Column(name = "image_url", nullable = false)
	@NotEmpty(message = "Image cannot be empty.")
	private String image;

	@Column(name = "description", nullable = false)
	@NotEmpty(message = "Description cannot be empty.")
	private String description;

	@Column(name = "date_created", nullable = true)
	private LocalDate createdOn;

	@Column(name = "is_top_product", nullable = false)
	private boolean topProduct;

	
	

}

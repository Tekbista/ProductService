package com.bista.productservice.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

	
	
	@OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private List<Image> image;

	@Column(name = "description", nullable = false)
	@NotEmpty(message = "Description cannot be empty.")
	private String description;

	@Column(name = "date_created", nullable = true)
	private LocalDate createdOn;

	@Column(name = "is_top_product", nullable = false)
	private boolean topProduct;

	@Column(name = "quantity")
	@Min(value = 1, message = "Quantity cannot be less than one.")
	private Integer quantity;
	

}

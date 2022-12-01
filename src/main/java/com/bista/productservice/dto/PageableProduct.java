package com.bista.productservice.dto;

import java.util.List;

import com.bista.productservice.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageableProduct {

	List<Product> products;
	Integer totalPage;
}

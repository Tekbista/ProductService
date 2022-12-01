package com.bista.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bista.productservice.entities.Category;

@Repository
public interface CategoryReposite extends JpaRepository<Category, Long>{

}

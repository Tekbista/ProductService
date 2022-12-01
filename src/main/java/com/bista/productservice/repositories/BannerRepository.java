package com.bista.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bista.productservice.entities.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long>{

}

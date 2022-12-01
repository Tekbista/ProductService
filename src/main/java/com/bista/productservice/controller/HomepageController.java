package com.bista.productservice.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bista.productservice.entities.Banner;
import com.bista.productservice.services.BannerService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/homepage")
public class HomepageController {

	@Autowired
	private BannerService bannerService;
	
	@GetMapping
	public ResponseEntity<String> home(){
		return ResponseEntity.status(HttpStatus.OK).body("I am alive.");
	}
	
	
	@PostMapping("/banner")
	public ResponseEntity<Banner> addBanner(@Valid @RequestBody Banner banner){
		Banner newBanner = bannerService.addBanner(banner);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newBanner);
	}
	
	
	@GetMapping("/banner")
	public ResponseEntity<List<Banner>> getAllBanners(){
		List<Banner> banners = bannerService.getAllBanners();
		return ResponseEntity.status(HttpStatus.OK).body(banners);
	}
	
	@GetMapping("banner/{id}")
	public ResponseEntity<Banner> getBanner(@PathVariable("id") Long id){
			Banner banner = bannerService.getBanner(id);
			
			return ResponseEntity.status(HttpStatus.OK).body(banner);
	}
	
	@PutMapping("banner/{id}")
	public ResponseEntity<Banner> updateBanner(@Valid @RequestBody Banner banner, @PathVariable("id") Long id){
		Banner newBanner = bannerService.updateBanner(banner, id);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newBanner);
	}
	
	@DeleteMapping("banner/{id}")
	public ResponseEntity<String> deleteBanner(@PathVariable("id") Long id){
		bannerService.deleteBanner(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Banner deleted successfully.");
	}
}

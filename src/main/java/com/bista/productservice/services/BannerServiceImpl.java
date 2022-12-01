package com.bista.productservice.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bista.productservice.entities.Banner;
import com.bista.productservice.exceptions.ResourceNotFoundException;
import com.bista.productservice.repositories.BannerRepository;

@Service
public class BannerServiceImpl implements BannerService{

	@Autowired
	private BannerRepository bannerRepository;
	
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(BannerService.class);
	
	@Override
	public Banner addBanner(Banner banner) {
		
		return bannerRepository.save(banner);
	}

	@Override
	public Banner getBanner(Long id) {
		
		if(!bannerRepository.existsById(id)) {
			logger.error("Error Get Banner: Banner not found with banner ID: " + id);
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Banner not found.");
		}
		return bannerRepository.findById(id).get();
	}

	@Override
	public List<Banner> getAllBanners() {
		
		return bannerRepository.findAll();
	}

	@Override
	public Banner updateBanner(Banner banner, Long id) {
		
		if(!bannerRepository.existsById(id)) {
			logger.error("Error Get Banner: Banner not found with banner ID: " + id);
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Banner not found.");
		}
		Banner dbBanner = bannerRepository.findById(id).get();
		
		if(banner.getName().equals("") || banner.getName() ==null) {
			banner.setName(dbBanner.getName());
		}
		
		if(banner.getImage().equals("") || banner.getImage() ==null) {
			banner.setImage(dbBanner.getImage());
		}
		
		banner.setId(id);
		
		return bannerRepository.save(banner);
	}

	@Override
	public void deleteBanner(Long id) {
		if(!bannerRepository.existsById(id)) {
			logger.error("Error Delete Banner: Banner with id: " + id + " not found.");
			throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Banner not found.");
		}
		bannerRepository.deleteById(id);
	}

}

package com.bista.productservice.services;

import java.util.List;

import com.bista.productservice.entities.Banner;

public interface BannerService {

	Banner addBanner(Banner banner);
	Banner getBanner(Long id);
	List<Banner> getAllBanners();
	Banner updateBanner(Banner banner, Long id);
	void deleteBanner(Long id);
}

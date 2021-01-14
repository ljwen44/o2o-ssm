package com.sgxy.o2o.service;

import java.util.List;

import com.sgxy.o2o.dto.BannerDto;

public interface BannerService {
	public int getAll();
	public List<BannerDto> getBanners(Integer page);
	public int addBanner(String bid,String img, String btime, String title, String content);
	public int delBanner(String bid);
	public int updBanner(String bid,String img,String btime, String title, String content);
}

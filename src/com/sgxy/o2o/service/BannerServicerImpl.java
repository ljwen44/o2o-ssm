package com.sgxy.o2o.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.dto.BannerDto;
import com.sgxy.o2o.mapper.BannerMapper;

@Service("bannerService")
public class BannerServicerImpl implements BannerService{
	@Autowired
	public BannerMapper bannerMapper;
	
	@Override
	public List<BannerDto> getBanners(Integer page) {
		// TODO Auto-generated method stub
		return bannerMapper.getBanners(page);
	}

	@Override
	public int addBanner(String bid, String img, String btime, String title, String content) {
		// TODO Auto-generated method stub
		return bannerMapper.addBanner(bid, img, btime, title, content);
	}

	@Override
	public int delBanner(String bid) {
		// TODO Auto-generated method stub
		return bannerMapper.delBanner(bid);
	}

	@Override
	public int updBanner(String bid, String img, String btime, String title, String content) {
		// TODO Auto-generated method stub
		return bannerMapper.updBanner(bid, img, btime, title, content);
	}

	@Override
	public int getAll() {
		// TODO Auto-generated method stub
		return bannerMapper.getAll();
	}

}

package com.sgxy.o2o.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.mapper.CoinSaveMapper;

@Service("coinsaveService")
public class CoinSaveServiceImpl implements CoinSaveService {
	@Autowired
	public CoinSaveMapper coinSaveMapper;
}

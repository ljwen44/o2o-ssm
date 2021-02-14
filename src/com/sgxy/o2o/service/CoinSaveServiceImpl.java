package com.sgxy.o2o.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.mapper.CoinSaveMapper;

@Service("coinsaveService")
public class CoinSaveServiceImpl implements CoinSaveService {
	@Autowired
	public CoinSaveMapper coinSaveMapper;

	@Override
	public int addCoinSave(String csid, String uid, Integer coin, Integer flag, String pid) {
		// TODO Auto-generated method stub
		return coinSaveMapper.addCoinSave(csid, uid, coin, flag,pid);
	}

	@Override
	public int updCoinSaveFlag(String csid) {
		// TODO Auto-generated method stub
		return coinSaveMapper.updCoinSaveFlag(csid);
	}

	@Override
	public int getCoinSave(String pid) {
		// TODO Auto-generated method stub
		return coinSaveMapper.getCoinSave(pid);
	}

	@Override
	public int updCoinTime(String pid, String time) {
		// TODO Auto-generated method stub
		return coinSaveMapper.updCoinTime(pid, time);
	}

	@Override
	public int delCoinByUIDandPID(String uid, String pid) {
		// TODO Auto-generated method stub
		return coinSaveMapper.delCoinByUIDandPID(uid, pid);
	}
}

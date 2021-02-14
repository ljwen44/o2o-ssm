package com.sgxy.o2o.service;

public interface CoinSaveService {
	public int addCoinSave(String csid, String uid, Integer coin, Integer flag, String pid);
	int updCoinSaveFlag(String csid);
	int getCoinSave(String pid);

	int updCoinTime(String pid, String time);
	int delCoinByUIDandPID(String uid, String pid);

}

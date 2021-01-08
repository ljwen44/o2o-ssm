package com.sgxy.o2o.service;

import java.util.List;

public interface SignService {
	// 签到
	public int addSign(String sid, String uid, String stime);
	 
	// 获取签到日期
	public List<String> getSign(String uid);
}

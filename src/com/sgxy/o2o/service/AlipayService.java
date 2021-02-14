package com.sgxy.o2o.service;

import java.util.Map;

public interface AlipayService {
	public Boolean aliNotify( Map<String, String> conversionParams);
	
	public int addAlipay(String apid,String uid,String time,String paynum, Integer type);
}

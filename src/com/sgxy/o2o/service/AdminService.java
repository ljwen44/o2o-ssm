package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

public interface AdminService {
	public List<Map<String, String>> getAnaUserType();
	public List<Map<String, String>> getUserNumByDate(String date);
	public List<Map<String, String>> getUserSignByDate(String date);
	public List<Map<String, String>> getSignByDateAndType(String date, String type);
	public List<Map<String, String>> getOrderByDateAndNum(String date, Integer num);
	public List<Map<String, String>> getCoinByDateAndNum(String date, Integer num);
}

package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

public interface AdminService {
	// 管理员首页统计数据开始
	public List<Map<String, String>> getAnaUserType();
	public List<Map<String, String>> getUserNumByDate(String date);
	public List<Map<String, String>> getUserSignByDate(String date);
	public List<Map<String, String>> getSignByDateAndType(String date, String type);
	public List<Map<String, String>> getOrderByDateAndNum(String date, Integer num);
	public List<Map<String, String>> getCoinByDateAndNum(String date, Integer num);
	public List<Map<String, String>> getCoinIn(String date, Integer num);
	public List<Map<String, String>> getCoinOut(String date, Integer num);
	// 管理员统计数据结束

	// 管理员兼职信息开始
	public List<Map<String, String>> getPJobByAdmin(String type, String pstatus, Integer page);
	public int getTotalByAdmin(String type, String pstatus);
	public String delPartJob(String pid, String uid);
	// 管理员兼职信息结束
	
}

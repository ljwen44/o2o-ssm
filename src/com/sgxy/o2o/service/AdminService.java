package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

public interface AdminService {
	// ����Ա��ҳͳ�����ݿ�ʼ
	public List<Map<String, String>> getAnaUserType();
	public List<Map<String, String>> getUserNumByDate(String date);
	public List<Map<String, String>> getUserSignByDate(String date);
	public List<Map<String, String>> getSignByDateAndType(String date, String type);
	public List<Map<String, String>> getOrderByDateAndNum(String date, Integer num);
	public List<Map<String, String>> getCoinByDateAndNum(String date, Integer num);
	public List<Map<String, String>> getCoinIn(String date, Integer num);
	public List<Map<String, String>> getCoinOut(String date, Integer num);
	// ����Աͳ�����ݽ���

	// ����Ա��ְ��Ϣ��ʼ
	public List<Map<String, String>> getPJobByAdmin(String type, String pstatus, Integer page);
	public int getTotalByAdmin(String type, String pstatus);
	public String delPartJob(String pid, String uid);
	// ����Ա��ְ��Ϣ����
	
}

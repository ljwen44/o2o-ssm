package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import com.sgxy.o2o.dto.OrderDto;

public interface OrderService {
	// ����pid��ȡ��������
	public OrderDto getOrderByPID(String pid);
	
	public List<Map<String, String>> getOrder(String uid, String flag);
	
	public int addOrder(String uid,String pid, String oid, String flag);

	int delOrder(String oid);
	// ��ɶ���
	int updOFlag(String oid, String flag, String time);
	
	// ��ȡ����״̬
	String getOFlag(String oid);
}

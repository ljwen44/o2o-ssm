package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import com.sgxy.o2o.dto.OrderDto;

public interface OrderService {
	// 根据pid获取订单详情
	public OrderDto getOrderByPID(String pid);
	
	public List<Map<String, String>> getOrder(String uid, String flag);
}

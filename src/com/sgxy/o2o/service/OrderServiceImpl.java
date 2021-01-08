package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.dto.OrderDto;
import com.sgxy.o2o.mapper.OrderMapper;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	public OrderMapper orderMapper;

	@Override
	public OrderDto getOrderByPID(String pid) {
		// TODO Auto-generated method stub
		return orderMapper.getOrderByPID(pid);
	}

	@Override
	public List<Map<String, String>> getOrder(String uid, String flag) {
		// TODO Auto-generated method stub
		return orderMapper.getOrder(uid, flag);
	}
	
}

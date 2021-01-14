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

	@Override
	public int addOrder(String uid, String pid, String oid, String flag) {
		// TODO Auto-generated method stub
		return orderMapper.addOrder(uid, pid, oid, flag);
	}

	@Override
	public int delOrder(String oid) {
		// TODO Auto-generated method stub
		return orderMapper.delOrder(oid);
	}

	@Override
	public int updOFlag(String oid, String flag, String time) {
		// TODO Auto-generated method stub
		return orderMapper.updOFlag(oid, flag, time);
	}

	@Override
	public String getOFlag(String oid) {
		// TODO Auto-generated method stub
		return orderMapper.getOFlag(oid);
	}
	
}

package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.mapper.AdminMapper;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	public AdminMapper adminMapper;

	@Override
	public List<Map<String, String>> getAnaUserType() {
		// TODO Auto-generated method stub
		return adminMapper.getAnaUserType();
	}

	@Override
	public List<Map<String, String>> getUserNumByDate(String date) {
		// TODO Auto-generated method stub
		return adminMapper.getUserNumByDate(date);
	}

	@Override
	public List<Map<String, String>> getUserSignByDate(String date) {
		// TODO Auto-generated method stub
		return adminMapper.getUserSignByDate(date);
	}

	@Override
	public List<Map<String, String>> getSignByDateAndType(String date, String type) {
		// TODO Auto-generated method stub
		return adminMapper.getSignByDateAndType(date, type);
	}

	@Override
	public List<Map<String, String>> getOrderByDateAndNum(String date, Integer num) {
		// TODO Auto-generated method stub
		return adminMapper.getOrderByDateAndNum(date, num);
	}

	@Override
	public List<Map<String, String>> getCoinByDateAndNum(String date, Integer num) {
		// TODO Auto-generated method stub
		return adminMapper.getCoinByDateAndNum(date, num);
	}
}

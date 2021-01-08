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
	public List<Map<String, String>> getUserSignAll() {
		// TODO Auto-generated method stub
		return adminMapper.getUserSignAll();
	}

	@Override
	public List<Map<String, String>> getUserSignInDate() {
		// TODO Auto-generated method stub
		return adminMapper.getUserSignInDate();
	}

	@Override
	public List<Map<String, String>> getUserRegTime() {
		// TODO Auto-generated method stub
		return adminMapper.getUserRegTime();
	}
}

package com.sgxy.o2o.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.dto.UserDto;
import com.sgxy.o2o.mapper.UserMapper;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDto getUserByEmailAndPwd(String email, String md5Password) {
		return userMapper.selectUserByEmailAndPassword(email, md5Password);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		return userMapper.getUserByEmail(email);
	}

	@Override
	public int addUser(String uid, String email, String password, String type, String phone, String userName, String regTime, String auth) {
		return userMapper.addUser(uid, email, password, type, phone, userName, regTime, auth);
	}

	@Override
	public String getUserByPhone(String phone) {
		return userMapper.getUserByPhone(phone);
	}

}

package com.sgxy.o2o.service;

import java.util.Map;

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
	public int addUser(String uid, String email, String password, 
			String type, String phone, String userName, 
			String regTime, Integer coin, String auth) {
		return userMapper.addUser(uid, email, password, type, phone, userName, regTime, coin, auth);
	}

	@Override
	public String getUserByPhone(String phone) {
		return userMapper.getUserByPhone(phone);
	}

	@Override
	public String getLogoutData(String uid) {
		// TODO Auto-generated method stub
		return userMapper.getLogoutData(uid);
	}

	@Override
	public int addLogout(String exid, String uid, String chat, String sign) {
		// TODO Auto-generated method stub
		return userMapper.addLogout(exid, uid, chat, sign);
	}

	@Override
	public int updData(String chat, String uid, String sign) {
		// TODO Auto-generated method stub
		return userMapper.updData(chat, uid, sign);
	}

	@Override
	public Map<String, String> getLogout(String uid) {
		// TODO Auto-generated method stub
		return userMapper.getLogout(uid);
	}


}

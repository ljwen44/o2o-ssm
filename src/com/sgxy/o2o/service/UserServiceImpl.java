package com.sgxy.o2o.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.dto.UserDto;
import com.sgxy.o2o.mapper.UserMapper;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	public UserMapper userMapper;

	@Override
	public int updPwd(String newpwd, String uid) {
		// TODO Auto-generated method stub
		return userMapper.updPwd(uid, newpwd);
	}

	@Override
	public UserDto findUserByUUID(String uid) {
		// TODO Auto-generated method stub
		return userMapper.findUserByUUID(uid);
	}

	@Override
	public int editUser(String avatar, String userName, String sex, String record, String school, String graduation,
			String city, String IDpass, String phone, String introduce, String uid) {
		// TODO Auto-generated method stub
		return userMapper.editUser(avatar, userName, sex, record, school, graduation, city, IDpass, phone, introduce, uid);
	}

	@Override
	public List<UserDto> getRecUser() {
		// TODO Auto-generated method stub
		return userMapper.getRecUser();
	}

	@Override
	public int addJFen(String uid) {
		// TODO Auto-generated method stub
		return userMapper.addJFen(uid);
	}

	@Override
	public int inteToCoin(String uid, Integer num) {
		// TODO Auto-generated method stub
		return userMapper.inteToCoin(uid, num);
	}

	@Override
	public int updAdmin(String uid, String userName, String phone, String avatar) {
		// TODO Auto-generated method stub
		return userMapper.updAdmin(uid, userName, phone, avatar);
	}

	@Override
	public List<UserDto> getAuthUser(Integer page, String userName) {
		// TODO Auto-generated method stub
		return userMapper.getAuthUser(page, userName);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return userMapper.getTotal();
	}

	@Override
	public int updStatus(String uid) {
		// TODO Auto-generated method stub
		return userMapper.updStatus(uid);
	}

	@Override
	public int updAuth(String uid) {
		// TODO Auto-generated method stub
		return userMapper.updAuth(uid);
	}

	@Override
	public List<UserDto> getUserByConditions(Integer page, String userName, String type) {
		// TODO Auto-generated method stub
		return userMapper.getUserByConditions(page, userName, type);
	}

	@Override
	public int getTotalByType(String type) {
		// TODO Auto-generated method stub
		return userMapper.getTotalByType(type);
	}

	@Override
	public int delUserCoin(String uid, Integer coin) {
		// TODO Auto-generated method stub
		return userMapper.delUserCoin(uid, coin);
	}

	@Override
	public int addUserCoin(String uid, Integer coin) {
		// TODO Auto-generated method stub
		return userMapper.addUserCoin(uid, coin);
	}

	@Override
	public int updAuthStatus(String uid) {
		// TODO Auto-generated method stub
		return userMapper.updAuthStatus(uid);
	}

	@Override
	public String getAuthStatus(String uid) {
		// TODO Auto-generated method stub
		return userMapper.getAuthStatus(uid);
	}
}

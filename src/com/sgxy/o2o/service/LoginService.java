package com.sgxy.o2o.service;

import java.util.Map;

import com.sgxy.o2o.dto.UserDto;


public interface LoginService {
	public UserDto getUserByEmailAndPwd(String userNumber, String md5Password);
	public UserDto getUserByEmail(String email);
	public String getUserByPhone(String phone);
	public int addUser(String uid, String email, String password, 
			String type, String phone, String userName, 
			String regTime,Integer coin, String auth);

	public int addLogout(String exid, String uid, String chat, String sign);
	public String getLogoutData(String uid);
	public int updData(String chat, String uid, String sign);
	Map<String, String> getLogout(String uid);
} 
 
 
 
 
 
 

 

 
 


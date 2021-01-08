package com.sgxy.o2o.service;

import com.sgxy.o2o.dto.UserDto;

public interface LoginService {
	public UserDto getUserByEmailAndPwd(String userNumber, String md5Password);
	public UserDto getUserByEmail(String email);
	public String getUserByPhone(String phone);
	public int addUser(String uid, String email, String password, String type, String phone, String userName, String regTime, String auth);
} 
 
 
 
 
 
 

 

 
 


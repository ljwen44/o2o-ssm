package com.sgxy.o2o.service;


import java.util.List;

import com.sgxy.o2o.dto.UserDto;

public interface UserService {
	// 根据uid查找用户
	public UserDto findUserByUUID(String uid);
	
	// 修改密码
	public int updPwd(String newpwd, String uid);

	// 修改资料
	public int editUser(String avatar,String userName,String sex,
			String record,String school,String graduation,
			String city,String IDpass,String phone,
			String introduce,String uid);
	
	// 获取推荐用户
	public List<UserDto> getRecUser();
	
	// 增加积分
	public int addJFen(String uid);
	
	// 兑换学币
	public int inteToCoin(String uid, Integer num);
	
	// 管理员修改密码
	public int updAdmin(String uid, String userName, String phone, String avatar);
	
	// 获取需要认证的用户
	public List<UserDto> getAuthUser(Integer page, String userName);
	// 获取认证用户的数量
	public int getTotal();
	public int updStatus(String uid);
	public int updAuth(String uid);
	public List<UserDto> getUserByConditions(Integer page, String userName, String type);
	public int getTotalByType(String type);
	
	
}

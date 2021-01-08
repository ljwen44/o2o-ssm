package com.sgxy.o2o.service;


import java.util.List;

import com.sgxy.o2o.dto.UserDto;

public interface UserService {
	// ����uid�����û�
	public UserDto findUserByUUID(String uid);
	
	// �޸�����
	public int updPwd(String newpwd, String uid);

	// �޸�����
	public int editUser(String avatar,String userName,String sex,
			String record,String school,String graduation,
			String city,String IDpass,String phone,
			String introduce,String uid);
	
	// ��ȡ�Ƽ��û�
	public List<UserDto> getRecUser();
	
	// ���ӻ���
	public int addJFen(String uid);
	
	// �һ�ѧ��
	public int inteToCoin(String uid, Integer num);
	
	// ����Ա�޸�����
	public int updAdmin(String uid, String userName, String phone, String avatar);
	
	// ��ȡ��Ҫ��֤���û�
	public List<UserDto> getAuthUser(Integer page, String userName);
	// ��ȡ��֤�û�������
	public int getTotal();
	public int updStatus(String uid);
	public int updAuth(String uid);
	public List<UserDto> getUserByConditions(Integer page, String userName, String type);
	public int getTotalByType(String type);
	
	
}
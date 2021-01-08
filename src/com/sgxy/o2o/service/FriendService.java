package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import com.sgxy.o2o.dto.FriendDto;

public interface FriendService {
	// ��ȡ�����б�,�������ʱ������,һ�η���ʮ������
	public List<Map<String, String>> getFriend(String uid, Integer page);
	
	// ��ȡ��������
	public int getFriendTotal(String uid);
	
	// ��Ӻ���
	public int addFriend(String fid, String uid, String ruid, String ftime);

	// ɾ������
	public int delFriend(String fid);
	
	public FriendDto getFriendIsExistByFID(String fid);

}

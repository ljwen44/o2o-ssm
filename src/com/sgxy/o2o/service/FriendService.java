package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import com.sgxy.o2o.dto.FriendDto;

public interface FriendService {
	// 获取好友列表,根据添加时间排序,一次返回十条数据
	public List<Map<String, String>> getFriend(String uid, Integer page);
	
	// 获取好友总数
	public int getFriendTotal(String uid);
	
	// 添加好友
	public int addFriend(String fid, String uid, String ruid, String ftime);

	// 删除好友
	public int delFriend(String fid);
	
	public FriendDto getFriendIsExistByFID(String fid);

}

package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;


public interface ChatService {
	int addChat(String cid, String uid, String message, 
			String ctime,Integer isRead, String ruid);
	
	void updIsRead(String uid, String ruid);
	List<Map<String, String>> getChatList(String uid, String ruid);
	List<Map<String, String>> getReadNum(String uid);
}

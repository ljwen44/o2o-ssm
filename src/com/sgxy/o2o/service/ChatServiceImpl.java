package com.sgxy.o2o.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.mapper.ChatMapper;

@Service("chatService")
public class ChatServiceImpl implements ChatService {
	@Autowired
	public ChatMapper chatMapper;

	@Override
	public int addChat(String cid, String uid, String message, String ctime, Integer isRead, String ruid) {
		// TODO Auto-generated method stub
		return chatMapper.addChat(cid, uid, message, ctime, isRead, ruid);
	}

	@Override
	public void updIsRead(String uid, String ruid) {
		// TODO Auto-generated method stub
		chatMapper.updIsRead(uid, ruid);
	}

	@Override
	public List<Map<String, String>> getChatList(String uid, String ruid) {
		// TODO Auto-generated method stub
		return chatMapper.getChatList(uid, ruid);
	}

	@Override
	public List<Map<String, String>> getReadNum(String uid) {
		// TODO Auto-generated method stub
		return chatMapper.getReadNum(uid);
	}
}

package com.sgxy.o2o.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgxy.o2o.mapper.ChatMapper;

@Service("chatService")
public class ChatServiceImpl implements ChatService {
	@Autowired
	public ChatMapper chatMapper;
}

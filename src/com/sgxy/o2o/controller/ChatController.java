package com.sgxy.o2o.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.sgxy.o2o.basic.controller.BasicController;
import com.sgxy.o2o.service.ChatService;

@Controller
@RequestMapping("/chatController")
public class ChatController extends BasicController{
	@Autowired
	@Qualifier("chatService")
	public ChatService chatService;
	
	@ModelAttribute
	@RequestMapping(value = "/getChatList", method = RequestMethod.POST)
	public void getChatList(HttpServletRequest request, HttpServletResponse response,
			String uid, String ruid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		List<Map<String, String>> list = chatService.getChatList(uid, ruid);
		chatService.updIsRead(uid, ruid);
		json.put("list", list);
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value = "/getNoReadList", method = RequestMethod.POST)
	public void getNoReadList(HttpServletRequest request, HttpServletResponse response,
			String uid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		List<Map<String , String>> list = chatService.getReadNum(uid);
		json.put("list", list);
		this.writeJson(json.toString(), response);
	}
	
}

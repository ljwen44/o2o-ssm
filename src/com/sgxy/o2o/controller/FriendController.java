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

import com.sgxy.o2o.basic.controller.BasicController;
import com.sgxy.o2o.dto.FriendDto;
import com.sgxy.o2o.service.FriendService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/friendController")
public class FriendController extends BasicController{
	@Autowired
	@Qualifier("friendService")
	public FriendService friendService;
	
	@ModelAttribute
	@RequestMapping(value="/getFriendByUID", method = RequestMethod.POST)
	public void getFriendByUID(HttpServletRequest request,HttpServletResponse response, 
			String uid, Integer page) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		Integer pages = page * 10;
		List<Map<String, String>> list = friendService.getFriend(uid, pages);
		json.put("list", list);
		
		int total = friendService.getFriendTotal(uid);
		json.put("total", total);
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/delFriendByFID", method = RequestMethod.POST)
	public void delFriendByFID(HttpServletRequest request,HttpServletResponse response, 
			String fid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		FriendDto friend = friendService.getFriendIsExistByFID(fid);
		if(friend == null) {
			json.put("message", "该好友不存在");
			this.writeJson(json.toString(), response);
			return ;
		}
		
		int delStatus = friendService.delFriend(fid);
		if(delStatus < 1) {
			json.put("message", "删除失败，请稍后重试!");
		}
		
		this.writeJson(json.toString(), response);
	}
}

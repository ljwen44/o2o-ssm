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
import com.sgxy.o2o.service.ApplyService;
import com.sgxy.o2o.service.FriendService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/friendController")
public class FriendController extends BasicController{
	@Autowired
	@Qualifier("friendService")
	public FriendService friendService;
	@Autowired
	@Qualifier("applyService")
	public ApplyService applyService;
	
	@ModelAttribute
	@RequestMapping(value="/getFriendByUID", method = RequestMethod.POST)
	public void getFriendByUID(HttpServletRequest request,HttpServletResponse response, 
			String uid, Integer page) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		Integer pages = (page-1) * 10;
		List<Map<String, String>> list = friendService.getFriend(uid, pages);
		json.put("list", list);
		
		int total = friendService.getFriendTotal(uid);
		json.put("total", total);
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/delFriendByFID", method = RequestMethod.POST)
	public void delFriendByFID(HttpServletRequest request,HttpServletResponse response, 
			String fid, String uid, String ruid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		FriendDto friend = friendService.getFriendIsExistByFID(fid);
		if(friend == null) {
			json.put("message", "∏√∫√”—≤ª¥Ê‘⁄");
			this.writeJson(json.toString(), response);
			return ;
		}
		
		int delStatus = friendService.delFriend(fid);
		if(delStatus < 1) {
			json.put("message", "…æ≥˝ ß∞‹£¨«Î…‘∫Û÷ÿ ‘!");
			this.writeJson(json.toString(), response);
			return ;
		}
		int delApplyStatus = applyService.delByDelFriend(uid, ruid);
		if(delApplyStatus < 1) {
			json.put("message", "…æ≥˝ ß∞‹£¨«Î…‘∫Û÷ÿ ‘!");
		}
		this.writeJson(json.toString(), response);
	}
}

package com.sgxy.o2o.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sgxy.o2o.basic.controller.BasicController;
import com.sgxy.o2o.service.ApplyService;
import com.sgxy.o2o.service.FriendService;
import com.sgxy.o2o.service.SysNoticeService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/applyController")
public class ApplyController extends BasicController{
	@Autowired
	@Qualifier("applyService")
	public ApplyService applyService;
	@Autowired
	@Qualifier("friendService")
	public FriendService friendService;
	@Autowired
	@Qualifier("sysNoticeService")
	public SysNoticeService sysNoticeService;
	
	@ModelAttribute
	@RequestMapping(value="/getApplyByUID", method = RequestMethod.POST)
	public void getApplyByUID(HttpServletRequest request,HttpServletResponse response,
			String uid, Integer page) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		Integer pages = (page-1) * 10;
		List<Map<String, String>> list = applyService.getApplyList(uid, pages);
		int total = applyService.getTotal(uid);
		json.put("list", list);
		json.put("total", total);
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/updApplyByUID", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED)
	public void updApplyByUID(HttpServletRequest request,HttpServletResponse response,
			String uid, String ruid, String aid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		int updStatus = applyService.updApply(aid);
		if(updStatus < 1) {
			json.put("message", "数据处理异常，请刷新重试");
			this.writeJson(json.toString(), response);
			return;
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ftime = df.format(new Date());
		
		String fid1 = this.getUUID();
		int addF1 = friendService.addFriend(fid1, uid, ruid, ftime);
		if(addF1 < 1) {
			json.put("message", "添加好友失败，请稍后重试!");
			this.writeJson(json.toString(), response);
			return;
		}
	
		this.writeJson(json.toString(), response);
	}

	@ModelAttribute
	@RequestMapping(value="/rejApplyByUID", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED)
	public void rejApplyByUID(HttpServletRequest request,HttpServletResponse response,
			String uid, String userName, String aid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		int delStatus = applyService.delApply(aid);
		if(delStatus < 1) {
			json.put("message", "操作失败，请稍后重试!");
			this.writeJson(json.toString(), response);
			return ;
		}
		
		String ssid = this.getUUID();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		String desc = "用户"+userName+"拒绝了您的好友申请";
		int addStatus = sysNoticeService.addSysNotice(ssid, uid, desc, time);
		if(addStatus < 1) {
			json.put("message", "操作失败，请稍后重试!");
			this.writeJson(json.toString(), response);
			return ;
		}
		
		this.writeJson(json.toString(), response);
	}

}

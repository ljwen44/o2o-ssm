package com.sgxy.o2o.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sgxy.o2o.basic.controller.BasicController;
import com.sgxy.o2o.dto.SysNoticeDto;
import com.sgxy.o2o.service.SysNoticeService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/sysNoticeController")
public class SysNoticeController extends BasicController{
	@Autowired
	@Qualifier("sysNoticeService")
	public SysNoticeService sysNoticeService;
	
	@ModelAttribute
	@RequestMapping(value="/getSysNotice", method = RequestMethod.POST)
	public void getSysNotice(HttpServletRequest request,HttpServletResponse response, String uid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		List<SysNoticeDto> sysList = sysNoticeService.getSysNotice(uid);
		json.put("sysList", sysList);
		
		this.writeJson(json.toString(), response);
	}

	@ModelAttribute
	@RequestMapping(value="/addSysNotice", method = RequestMethod.POST)
	public void addSysNotice(HttpServletRequest request,HttpServletResponse response,
			 String uid,  String desc) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		String ssid = this.getUUID();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		int status = sysNoticeService.addSysNotice(ssid, uid, desc, time);
		if(status < 1) {
			json.put("message", "·¢ËÍÊ§°Ü£¬ÇëÉÔºóÖØÊÔ!");
		}
		
		this.writeJson(json.toString(), response);
	}
	
}



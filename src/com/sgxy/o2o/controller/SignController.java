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
import com.sgxy.o2o.service.SignService;
import com.sgxy.o2o.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("signController")
public class SignController extends BasicController{
	@Autowired
	@Qualifier("signService")
	public SignService signService;
	@Autowired
	@Qualifier("userService")
	public UserService userService;
	
	@ModelAttribute
	@RequestMapping(value="/addSign", method = RequestMethod.POST)
	public void addSign(HttpServletRequest request,HttpServletResponse response, String uid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		String sid = this.getUUID();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stime = df.format(new Date());
		int status = signService.addSign(sid, uid, stime);
		if(status < 1) {
			json.put("message", "Ç©µ½Ê§°Ü£¬ÇëË¢ÐÂÖØÊÔ!");
			this.writeJson(json.toString(), response);
			return ;
		}
		int flag = userService.addJFen(uid);
		System.out.println(flag);
		this.writeJson(json.toString(), response);
	}

	@ModelAttribute
	@RequestMapping(value="/getSign", method = RequestMethod.POST)
	public void getSign(HttpServletRequest request,HttpServletResponse response, String uid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		List<String> list = signService.getSign(uid);
		json.put("list", list);
		this.writeJson(json.toString(), response);
	}
}

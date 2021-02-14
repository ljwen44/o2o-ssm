package com.sgxy.o2o.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sgxy.o2o.basic.controller.BasicController;
import com.sgxy.o2o.util.Phone;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/phoneController")
public class PhoneController extends BasicController{
	private static Phone phoneUtil = new Phone();
	private Date stime;
	private Date etime;
	private String code;
	
	
	@ModelAttribute
	@RequestMapping(value = "/getCodeByPhone", method = RequestMethod.POST)
	public void getCodeByPhone(HttpServletRequest request, HttpServletResponse response,
			String phone) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		if(phone == null || phone == "") {
			json.put("message", "号码接收为空");
			this.writeJson(json.toString(), response);
			return ;
		}
		code = (int) Math.floor(Math.random()*10000) + "";
		int flag = phoneUtil.sendCode(phone, code);
		if(flag < 1) {
			json.put("message", "发送失败,请检查手机号码是否正确");
		} else {			
			stime = new Date();
		}
		System.err.println("发送验证码成功，验证码为:" + code);
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value = "/validateCode", method = RequestMethod.POST)
	public void validateCode(HttpServletRequest request, HttpServletResponse response,
			String validate) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		if(code == null) {
			json.put("message", "请先发送验证码");
			this.writeJson(json.toString(), response);
			return;
		}
		etime = new Date();
		int time = (int) (etime.getTime() - stime.getTime()); // 时间差 1000*60*5
		if(time > 300000) {
			json.put("message", "验证码超时，请重新发送");
			this.writeJson(json.toString(), response);
			return;
		}
		System.err.println("收到的验证码为：" + validate);
		System.err.println("之前发送的验证码为：" + code);
		if(!code.equals(validate)) {
			json.put("message", "验证码不正确");
		}
		this.writeJson(json.toString(), response);
	}
	
}

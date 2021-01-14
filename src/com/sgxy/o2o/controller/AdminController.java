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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sgxy.o2o.basic.controller.BasicController;
import com.sgxy.o2o.service.AdminService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/adminController")
public class AdminController extends BasicController{
	@Autowired
	@Qualifier("adminService")
	public AdminService adminService;
	
	@ModelAttribute
	@RequestMapping(value="/getAnaUserType", method = RequestMethod.POST)
	public void getAnaUserType(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		List<Map<String, String>> barData = adminService.getAnaUserType();
		json.put("barData", barData);
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/getUserNumByDate", method = RequestMethod.POST)
	public void getUserNumByDate(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		List<Map<String, String>> userNumList = adminService.getUserNumByDate(date);
		json.put("userNumList", userNumList);
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/getBarLineByDate", method = RequestMethod.POST)
	public void getBarLineByDate(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		
		List<Map<String, String>> stuList = adminService.getSignByDateAndType(date, "学员");
		List<Map<String, String>> teaList = adminService.getSignByDateAndType(date, "教员");
		json.put("stuList", stuList);
		json.put("teaList", teaList);
		
		// 连续七天签到人数
		List<Map<String, String>> userSignList = adminService.getUserSignByDate(date);
		json.put("userSignList", userSignList);
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/getOrderByDate", method = RequestMethod.POST)
	public void getOrderByDate(HttpServletRequest request, HttpServletResponse response,
			String date, Integer num) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		System.err.println(date);
		System.err.println(num);
		
		// 成交量
		List<Map<String, String>> orderList = adminService.getOrderByDateAndNum(date, num);
		json.put("orderList", orderList);
		
		// 成交金额
		List<Map<String, String>> coinList = adminService.getCoinByDateAndNum(date, num);
		json.put("coinList", coinList);
		
		this.writeJson(json.toString(), response);
	}
	
	
}

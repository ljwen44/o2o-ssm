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
}

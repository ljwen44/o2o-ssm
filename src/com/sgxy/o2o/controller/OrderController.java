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
import com.sgxy.o2o.dto.ProgressDto;
import com.sgxy.o2o.service.OrderService;
import com.sgxy.o2o.service.PartJobService;
import com.sgxy.o2o.service.ProgressService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/orderController")
public class OrderController extends BasicController{
	@Autowired
	@Qualifier("orderService")
	public OrderService orderService;
	@Autowired
	@Qualifier("partjobService")
	public PartJobService partjobService;
	@Autowired
	@Qualifier("progressService")
	public ProgressService progressService;
	@ModelAttribute
	@RequestMapping(value = "/getOrder", method = RequestMethod.POST)
	public void getOrder(HttpServletRequest request,HttpServletResponse response,
			String uid, String flag) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		List<Map<String, String>> orderList = orderService.getOrder(uid, flag);
		json.put("list", orderList);
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value = "/getOrderDetail", method = RequestMethod.POST)
	public void getOrderDetail(HttpServletRequest request,HttpServletResponse response,
			String pid, String oid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		Map<String, String> item = partjobService.getPJob(pid);
		List<ProgressDto> progressList = progressService.getProgressByOID(oid);
		json.put("item", item);
		json.put("progressList", progressList);
		
		this.writeJson(json.toString(), response);
	}
}

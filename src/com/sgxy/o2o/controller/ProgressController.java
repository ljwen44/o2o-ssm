package com.sgxy.o2o.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.sgxy.o2o.service.ProgressService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/progressController")
public class ProgressController extends BasicController{
	@Autowired
	@Qualifier("progressService")
	public ProgressService progressService;
	@Autowired
	@Qualifier("orderService")
	public OrderService orderService;
	
	@ModelAttribute
	@RequestMapping(value="/addProgress", method = RequestMethod.POST)
	public void addProgress(HttpServletRequest request,HttpServletResponse response,
			String oid, String content) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		String flag = orderService.getOFlag(oid);
		if(flag.equals("已完成")) {
			json.put("message", "该订单已完成，无法添加进度");
			this.writeJson(json.toString(), response);
			return;
		}
		String ppid = this.getUUID();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pptime = df.format(new Date());
		int status = progressService.addProgress(oid, ppid, content, pptime);
		if(status < 1) {
			json.put("message", "添加失败，请稍后重试!");
			this.writeJson(json.toString(), response);
			return ;
		}
		ProgressDto progress = new ProgressDto();
		progress.setContent(content);
		progress.setOrderUUID(oid);
		progress.setPpTime(pptime);
		progress.setPpUUID(ppid);
		json.put("progress", progress);
		this.writeJson(json.toString(), response);
	}
}

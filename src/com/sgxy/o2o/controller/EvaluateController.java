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
import com.sgxy.o2o.service.EvaluateService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/evaluateController")
public class EvaluateController extends BasicController{
	@Autowired
	@Qualifier("evaluateService")
	public EvaluateService evaluateService;
	
	@ModelAttribute
	@RequestMapping(value="/getEvaListByUID", method = RequestMethod.POST)
	public void getEvaListByUID(HttpServletRequest request,HttpServletResponse response, String uid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		List<Map<String, String>> list = evaluateService.getEvaListByUID(uid);
		json.put("list", list);
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/addEvaluate", method = RequestMethod.POST)
	public void addEvaluate(HttpServletRequest request,HttpServletResponse response, 
			String rate, String uid, String content, String ruid, String oid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		String eid = this.getUUID();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String etime = df.format(new Date());
		int status = evaluateService.addEvaluate(eid, content, oid, uid, etime, rate, ruid);
		if(status < 1) {
			json.put("message", "ÆÀ¼ÛÊ§°Ü£¬ÇëË¢ÐÂÖØÊÔ!");
		}
		this.writeJson(json.toString(), response);
	}
}

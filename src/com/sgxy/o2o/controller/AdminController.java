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
import com.sgxy.o2o.service.AdminService;
import com.sgxy.o2o.service.AlipayService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/adminController")
public class AdminController extends BasicController{
	@Autowired
	@Qualifier("adminService")
	public AdminService adminService;
	@Autowired
	@Qualifier("alipayService")
	public AlipayService alipayService;
	
	
	// ----------------����Աͳ������չʾ��ʼ------------------------
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
		
		List<Map<String, String>> stuList = adminService.getSignByDateAndType(date, "ѧԱ");
		List<Map<String, String>> teaList = adminService.getSignByDateAndType(date, "��Ա");
		json.put("stuList", stuList);
		json.put("teaList", teaList);
		
		// ��������ǩ������
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
		
		// �ɽ���
		List<Map<String, String>> orderList = adminService.getOrderByDateAndNum(date, num);
		json.put("orderList", orderList);
		
		// �ɽ����
		List<Map<String, String>> coinList = adminService.getCoinByDateAndNum(date, num);
		json.put("coinList", coinList);
		
		this.writeJson(json.toString(), response);
	}
	
	// ----------------����Աͳ������չʾ����------------------------
	
	// ----------------����Ա�鿴ѧ��֧�����뿪ʼ---------------------
	@ModelAttribute
	@RequestMapping(value = "/getCoinOutAndIn", method = RequestMethod.POST)
	public void getCoinOutAndIn(HttpServletRequest request, HttpServletResponse response,
			String date, Integer num) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		// ����
		List<Map<String, String>> coinIn = adminService.getCoinIn(date, num);
		json.put("In", coinIn);
//		List<Map<String, String>> coinOut = adminService.getCoinOut(date, num);
//		json.put("Out", coinOut);
		this.writeJson(json.toString(), response);
	}
	
	// ----------------����Ա�鿴ѧ��֧���������---------------------
	
	
	
	// ----------------����Ա��ְ��Ϣ������ʼ------------------------
	// ��������ɸѡ��ְ��Ϣ
	@ModelAttribute
	@RequestMapping(value = "/getPJobByAdmin", method = RequestMethod.POST)
	public void getPJobByAdmin(HttpServletRequest request, HttpServletResponse response,
			String type, String pstatus, Integer page) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		Integer pages = (page-1)*10;
		List<Map<String, String>> list = adminService.getPJobByAdmin(type, pstatus, pages);
		json.put("list", list);
		
		int total = adminService.getTotalByAdmin(type, pstatus);
		json.put("total", total);
		
		this.writeJson(json.toString(), response);
	}
	
	// ����pidɾ����Ϣ
	@ModelAttribute
	@RequestMapping(value = "/delPJobByPid", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED)
	public void delPJobByPid(HttpServletRequest request, HttpServletResponse response,
			String pid, String uid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		String msg = adminService.delPartJob(pid, uid);
		if(!msg.equals("OK")) {
			json.put("message", msg);
		}
		
		this.writeJson(json.toString(), response);
	}
	
	// ----------------����Ա��ְ��Ϣ��������------------------------
}

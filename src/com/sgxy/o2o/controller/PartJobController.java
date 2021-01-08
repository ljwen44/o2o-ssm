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
import com.sgxy.o2o.dto.OrderDto;
import com.sgxy.o2o.dto.PartTimeJobDto;
import com.sgxy.o2o.dto.ProgressDto;
import com.sgxy.o2o.dto.UserDto;
import com.sgxy.o2o.service.EvaluateService;
import com.sgxy.o2o.service.OrderService;
import com.sgxy.o2o.service.PartJobService;
import com.sgxy.o2o.service.ProgressService;
import com.sgxy.o2o.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/jobController")
public class PartJobController extends BasicController{
	@Autowired
	@Qualifier("partjobService")
	public PartJobService partjobService;
	@Autowired
	@Qualifier("userService")
	public UserService userService;
	@Autowired
	@Qualifier("orderService")
	public OrderService orderService;
	@Autowired
	@Qualifier("progressService")
	public ProgressService progressService;
	@Autowired
	@Qualifier("evaluateService")
	public EvaluateService evaluateService;
	
	@ModelAttribute
	@RequestMapping(value="/getAllJob", method = RequestMethod.POST)
	public void getAllJob(HttpServletRequest request,HttpServletResponse response,
			String province, String city, String block, 
			String minSalary, String maxSalary, int page, int type) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		int pageNum = (page-1) * 8;
		List<Map<String, String>> allJob = partjobService.getAllJob(province, city, block, minSalary, maxSalary, pageNum);
		json.put("list", allJob);
		if(type == 1) {
			int total = partjobService.getTotal();
			json.put("total", total);
		}
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/addJob", method = RequestMethod.POST)
	public void addJob(HttpServletRequest request,HttpServletResponse response,
			String uid, String userName, String userPhone,
			String province, String city, String block,
			String address, String minSalary, String maxSalary,
			String startTime, String endTime, String content,
			String precord) {
		JSONObject json = new JSONObject();//要返回到页面的对象
		json.put("message", ""); // 在对象中先添加入一个数据
		String pid = this.getUUID();
		String pstatus = "进行中";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ptime = df.format(new Date());
		int status = partjobService.addJob(pid, uid, userName, userPhone, province, city, block, address, minSalary, maxSalary, startTime, endTime, content, ptime, pstatus, precord);
		if(status < 1) {
			json.put("message", "服务器错误，请刷新重新!");
		}
		this.writeJson(json.toString(), response);// 将数据返回到页面
	}

	@ModelAttribute
	@RequestMapping(value="/findInfoByUID", method = RequestMethod.POST)
	public void findInfoByUID(HttpServletRequest request,HttpServletResponse response, String uid) {
		JSONObject json = new JSONObject();//要返回到页面的对象
		json.put("message", ""); // 在对象中先添加入一个数据
		List<PartTimeJobDto> infoList = partjobService.findInfoByUID(uid);
		json.put("list", infoList);
		this.writeJson(json.toString(), response);// 将数据返回到页面
	}
	
	@ModelAttribute
	@RequestMapping(value="/getPartJobByPID", method = RequestMethod.POST)
	public void getPartJobByPID(HttpServletRequest request,HttpServletResponse response, String pid) {
		JSONObject json = new JSONObject();//要返回到页面的对象
		json.put("message", ""); // 在对象中先添加入一个数据
		boolean status = false; // 兼职是否被教员接收
		PartTimeJobDto partJob = partjobService.getTimeJobByPID(pid);
		Map<String, String> info = partjobService.getPJob(pid);
		json.put("info", info);
		OrderDto order = orderService.getOrderByPID(pid);
		if(order != null) {
			status = true;
			// 订单完成状态
			json.put("ostatus", order.getFlag());
			json.put("oid", order.getOid());
			
			if(evaluateService.getEvaluateByOID(order.getOid())!=null) {
				json.put("estatus", true);
			} else {
				json.put("estatus", false);
			}
			
			// 获取教员信息
			UserDto userTeach = userService.findUserByUUID(order.getUid());
			json.put("userTeach", userTeach);
			
			// 获取进度安排
			List<ProgressDto> progressList = progressService.getProgressByOID(order.getOid());
			if(progressList.size() > 0) {
				json.put("progressList", progressList);
			}
		}
		json.put("status", status);
		json.put("partJob", partJob);
		this.writeJson(json.toString(), response);// 将数据返回到页面
	}
	
}

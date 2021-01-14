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
import com.sgxy.o2o.dto.ProgressDto;
import com.sgxy.o2o.service.CoinSaveService;
import com.sgxy.o2o.service.OrderService;
import com.sgxy.o2o.service.PartJobService;
import com.sgxy.o2o.service.ProgressService;
import com.sgxy.o2o.service.UserService;

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
	@Autowired
	@Qualifier("coinsaveService")
	public CoinSaveService coinsaveService;
	@Autowired
	@Qualifier("userService")
	public UserService userService;
	
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
		String flag = orderService.getOFlag(oid);
		json.put("item", item);
		json.put("progressList", progressList);
		json.put("ostatus", flag);
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public void addOrder(HttpServletRequest request,HttpServletResponse response,
			String uid, String pid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		// �ж��Ƿ񱻽ӵ�
		String pstatus = partjobService.getPstatus(pid);
		if(!pstatus.equals("δ�ӵ�")) {
			json.put("message", "��ְλ�ѱ��ӵ�");
			this.writeJson(json.toString(), response);
			return ;
		}
		
		String oid = this.getUUID();
		int addStatus = orderService.addOrder(uid, pid, oid, "������");
		if(addStatus < 1) {
			json.put("message", "�ӵ�ʧ�ܣ����Ժ�����!");
			this.writeJson(json.toString(), response);
			return;
		}
		int updStatus = partjobService.updStatus(pid, "������");
		if(updStatus < 1) {
			json.put("message", "�ӵ�ʧ�ܣ����Ժ�����!");
		}
		this.writeJson(json.toString(), response);
	}

	@ModelAttribute
	@RequestMapping(value = "/delOrder", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED)
	public void delOrder(HttpServletRequest request,HttpServletResponse response,
			String oid, String pid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		int delStatus = orderService.delOrder(oid);
		if(delStatus < 1) {
			json.put("message", "ȡ��ʧ�ܣ����Ժ�����!");
			this.writeJson(json.toString(), response);
			return ;
		}
		// ��ְ��Ϣ����״̬
		int updStatus = partjobService.updStatus(pid, "δ�ӵ�");
		if(updStatus < 1) {
			json.put("message", "ȡ��ʧ�ܣ����Ժ�����!");
			this.writeJson(json.toString(), response);
			return;
		}
		
		try {
			progressService.delProgress(oid);
		} catch (Exception e) {
			json.put("message", "ȡ��ʧ�ܣ����Ժ�����");
		}
		
		this.writeJson(json.toString(), response);
	}

	@ModelAttribute
	@RequestMapping(value = "/finishOrder", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED)
	public void finishOrder(HttpServletRequest request,HttpServletResponse response,
			String oid, String pid, String uid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());
		// �������״̬�޸�
		int updOStatus = orderService.updOFlag(oid, "�����", date);
		if(updOStatus < 1) {
			json.put("message", "����ʧ�ܣ����Ժ�����!");
			this.writeJson(json.toString(), response);
			return ;
		}
		
		int updCoinStatus = coinsaveService.updCoinTime(pid, date);
		if(updCoinStatus < 1) {
			json.put("message", "����ʧ�ܣ����Ժ�����!");
			this.writeJson(json.toString(), response);
			return ;
		}
		
		// ��ѧ�Ҹ�����Ա
		int coin = coinsaveService.getCoinSave(pid);
		// ��Ա���ѧ��
		int addStatus = userService.addUserCoin(uid, coin);
		if(addStatus < 1) {
			json.put("message", "����ʧ�ܣ����Ժ�����!");
			this.writeJson(json.toString(), response);
			return;
		}
		
		// ��ְ��Ϣ����״̬
		int updStatus = partjobService.updStatus(pid, "�����");
		if(updStatus < 1) {
			json.put("message", "����ʧ�ܣ����Ժ�����!");
		}
		this.writeJson(json.toString(), response);
	}
	
	
}

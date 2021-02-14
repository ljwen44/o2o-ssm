package com.sgxy.o2o.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sgxy.o2o.basic.controller.BasicController;
import com.sgxy.o2o.dto.OrderDto;
import com.sgxy.o2o.mapper.AdminMapper;

@Service("adminService")
public class AdminServiceImpl extends BasicController implements AdminService{
	@Autowired
	public AdminMapper adminMapper;
	
	@Autowired
	@Qualifier("partjobService")
	public PartJobService partjobService;
	@Autowired
	@Qualifier("orderService")
	public OrderService orderService;
	@Autowired
	@Qualifier("userService")
	public UserService userService;
	@Autowired
	@Qualifier("coinsaveService")
	public CoinSaveService coinsaveService;
	@Autowired
	@Qualifier("sysNoticeService")
	public SysNoticeService sysNoticeService;

	@Override
	public List<Map<String, String>> getAnaUserType() {
		// TODO Auto-generated method stub
		return adminMapper.getAnaUserType();
	}

	@Override
	public List<Map<String, String>> getUserNumByDate(String date) {
		// TODO Auto-generated method stub
		return adminMapper.getUserNumByDate(date);
	}

	@Override
	public List<Map<String, String>> getUserSignByDate(String date) {
		// TODO Auto-generated method stub
		return adminMapper.getUserSignByDate(date);
	}

	@Override
	public List<Map<String, String>> getSignByDateAndType(String date, String type) {
		// TODO Auto-generated method stub
		return adminMapper.getSignByDateAndType(date, type);
	}

	@Override
	public List<Map<String, String>> getOrderByDateAndNum(String date, Integer num) {
		// TODO Auto-generated method stub
		return adminMapper.getOrderByDateAndNum(date, num);
	}

	@Override
	public List<Map<String, String>> getCoinByDateAndNum(String date, Integer num) {
		// TODO Auto-generated method stub
		return adminMapper.getCoinByDateAndNum(date, num);
	}

	@Override
	public List<Map<String, String>> getPJobByAdmin(String type, String pstatus, Integer page) {
		// TODO Auto-generated method stub
		return adminMapper.getPJobByAdmin(type, pstatus, page);
	}

	@Override
	public int getTotalByAdmin(String type, String pstatus) {
		// TODO Auto-generated method stub
		return adminMapper.getTotalByAdmin(type, pstatus);
	}

	/**
	 * 1. 判断该兼职是否被接单和已完成
	 *  1.1  被接单：需要将删除信息发送给发布者和教员，同时修改相关数据表
	 *  1.2  已完成：提示管理员订单已完成，无法删除
	 *  1.3  未接单：直接删除兼职信息，并发送信息给发布者，同时修改相关数据表
	 * 2. 完成 1 的操作之后，将删除信息返回给发布者和教员
	 *  2.1  根据pid查找发布者和教员
	 *  2.2  返回操作结果给管理员
	 * **/
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String delPartJob(String pid, String uid) {
		String pstatus = partjobService.getPstatus(pid);
		if(pstatus.equals("未接单")) {
			int coin = coinsaveService.getCoinSave(pid);
			int addUserCoin = userService.addUserCoin(uid, coin);
			System.err.println(addUserCoin);
			String ssid = this.getUUID();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = df.format(new Date());
			String desc = "您发布的兼职信息涉嫌违规，请按照规定发布！";
			int addSys = sysNoticeService.addSysNotice(ssid, uid, desc, time);
			System.err.println(addSys);
			int delStatus = partjobService.delPJob(pid);
			int delCoin = coinsaveService.delCoinByUIDandPID(uid, pid);
			if(delStatus < 1 || delCoin < 1) {
				return "删除失败，请稍后重试!";
			}
		} else if(pstatus.equals("已完成")) {
			return "该兼职已完成，无法删除!";
		} else {
			OrderDto order = orderService.getOrderByPID(pid);
			int coin = coinsaveService.getCoinSave(pid);
			int addUserCoin = userService.addUserCoin(uid, coin);
			System.err.println(addUserCoin);
			String ssid = this.getUUID();
			String ssid1 = this.getUUID();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = df.format(new Date());
			String desc = "您发布的兼职信息涉嫌违规，请按照规定发布！";
			String desc1 = "您所接单的兼职信息涉嫌违规，已被删除，请联系发布者重新发布!";
			int addSys = sysNoticeService.addSysNotice(ssid, uid, desc, time);
			int addSys1 = sysNoticeService.addSysNotice(ssid1, order.getUid(), desc1, time);
			System.err.println(addSys + "   " + addSys1);
			int delStatus = partjobService.delPJob(pid);
			int delOrder = orderService.delOrder(order.getOid());
			int delCoin = coinsaveService.delCoinByUIDandPID(uid, pid);
			if(delStatus < 1 || delCoin < 1 || delOrder < 1) {
				return "删除失败，请稍后重试!";
			}
		}
		return "OK";
	}

	@Override
	public List<Map<String, String>> getCoinIn(String date, Integer num) {
		// TODO Auto-generated method stub
		return adminMapper.getCoinIn(date, num);
	}

	@Override
	public List<Map<String, String>> getCoinOut(String date, Integer num) {
		// TODO Auto-generated method stub
		return adminMapper.getCoinOut(date, num);
	}
}

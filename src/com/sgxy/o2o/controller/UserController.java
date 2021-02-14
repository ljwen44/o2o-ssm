package com.sgxy.o2o.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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

import com.sgxy.o2o.util.Base64Utils;
import com.sgxy.o2o.basic.controller.BasicController;
import com.sgxy.o2o.dto.PartTimeJobDto;
import com.sgxy.o2o.dto.UserDto;
import com.sgxy.o2o.service.EvaluateService;
import com.sgxy.o2o.service.PartJobService;
import com.sgxy.o2o.service.SysNoticeService;
import com.sgxy.o2o.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/userController")
public class UserController extends BasicController{
	@Autowired
	@Qualifier("userService")
	public UserService userService;
	@Autowired
	@Qualifier("evaluateService")
	public EvaluateService evaluateService;
	@Autowired
	@Qualifier("partjobService")
	public PartJobService partjobService;
	@Autowired
	@Qualifier("sysNoticeService")
	public SysNoticeService sysNoticeService;
	
	@Autowired
	ServletContext context;
	
	@ModelAttribute
	@RequestMapping(value = "/updAuthStatus", method = RequestMethod.POST)
	public void updAuthStatus(HttpServletRequest request,HttpServletResponse response, String uid) {
		JSONObject json = new JSONObject();//Ҫ���ص�ҳ��Ķ���
		json.put("message", ""); // �ڶ������������һ������
		
		String authStatus = userService.getAuthStatus(uid);
		if(authStatus.equals("������")) {
			json.put("message", "����������֤�������ظ�����");
			this.writeJson(json.toString(), response);
			return;
		}
		int flag = userService.updAuthStatus(uid);
		if(flag < 1) {
			json.put("message", "����ʧ�ܣ����Ժ�����!");
		}
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/updpwd", method = RequestMethod.POST)
	public void updpwd(HttpServletRequest request,HttpServletResponse response,String oldpwd,String newpwd, String uid) {
		JSONObject json = new JSONObject();//Ҫ���ص�ҳ��Ķ���
		json.put("message", ""); // �ڶ������������һ������
		UserDto user = userService.findUserByUUID(uid);
		String oldpassword = this.md5(oldpwd);
		if(!oldpassword.equals(user.getPassword())) {
			json.put("message", "������������������룡");
			this.writeJson(json.toString(), response);
			return ;
		}
		String newpassword = this.md5(newpwd);
		int status = userService.updPwd(newpassword, uid);
		if(status < 1) {
			json.put("message", "�������������Ժ�����!");
		}
		this.writeJson(json.toString(), response);
	}

	// ���ݵ绰�����޸�����
	@ModelAttribute
	@RequestMapping("/updByPhone")
	public void edit(HttpServletRequest request,HttpServletResponse response,
			String phone, String password) {
		JSONObject json = new JSONObject();//Ҫ���ص�ҳ��Ķ���
		json.put("message", ""); // �ڶ������������һ������
		
		String isExist = userService.getUidByPhone(phone);
		if(isExist == null || isExist == "") {
			json.put("message", "���ֻ�����δ��ע��");
			this.writeJson(json.toString(), response);
			return ;
		}
		
		String pwd = this.md5(password);
		int flag = userService.updByPhone(phone, pwd);
		if(flag < 1) {
			json.put("message", "�޸�ʧ�ܣ����Ժ�����!");
		}
		
		this.writeJson(json.toString(), response);
	}
	
	
	
	@ModelAttribute
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public void edit(HttpServletRequest request,HttpServletResponse response,String avatar, String userName,
			String sex, String record, String school, String graduation, String city, String IDpass,
			String phone, String introduce, String uid) {
		JSONObject json = new JSONObject();//Ҫ���ص�ҳ��Ķ���
		json.put("message", ""); // �ڶ������������һ������
		UserDto user = userService.findUserByUUID(uid);
		if(avatar.startsWith("data:image/jpeg;")) {
			// Ϊ������޸���ͷ��
			String ImageName = this.getUUID() +".jpg";
			String path = context.getRealPath("/");
			String headImageUrl = path + "images\\" + ImageName ;
			// �洢��ʹ�õ��Ǳ��ش洢
			// ����·����
			// ����һ��ͼƬ������
			System.err.println(path);
			System.err.println(headImageUrl);
			try {
				if(Base64Utils.GenerateImage(avatar, headImageUrl)) {
					if(user.getAvatar() != null) {
						File file = new File(path +  user.getAvatar());
						if(file.exists() && file.isFile()){//�ж��ļ�Ŀ¼�Ƿ���ڣ��Ƿ����ļ�
							System.err.println("ɾ��ͼƬ");
							file.delete();
						}
					}
					user.setAvatar("images/" + ImageName);
				}else {
					json.put("message", "ͼƬ�ϴ�����");
					this.writeJson(json.toString(), response);
					return ;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(IDpass.startsWith("data:image/jpeg;")) {
			String ImageName = this.getUUID() +".jpg";
			String path = context.getRealPath("/");
			String headImageUrl = path + "images\\" + ImageName ;
			try {
				if(Base64Utils.GenerateImage(avatar, headImageUrl)) {
					if(user.getIDpass() != null) {
						File file = new File(path +  user.getAvatar());
						if(file.exists() && file.isFile()){//�ж��ļ�Ŀ¼�Ƿ���ڣ��Ƿ����ļ�
							System.err.println("ɾ��ͼƬ");
							file.delete();
						}
					}
					user.setIDpass("images/" + ImageName);
				}else {
					json.put("message", "ͼƬ�ϴ�����");
					this.writeJson(json.toString(), response);
					return ;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.err.println(user.getAvatar());
		int status = userService.editUser(user.getAvatar(), userName, sex, record, school, graduation, city, user.getIDpass(), phone, introduce, uid);
		if(status < 1) {
			json.put("message", "�������������Ժ�����!");
		}
		this.writeJson(json.toString(), response);
	}

	@ModelAttribute
	@RequestMapping(value="/getUserDeatailByUID", method = RequestMethod.POST)
	public void getUserDeatailByUID(HttpServletRequest request,HttpServletResponse response, String uid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		UserDto user = userService.findUserByUUID(uid);
		user.setPassword("");
		json.put("user", user); // �û���ϸ����
		
		List<Map<String, String>> list = evaluateService.getEvaListByUID(uid);
		json.put("list", list); // �û������б�
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/getRecUser", method = RequestMethod.POST)
	public void getRecUser(HttpServletRequest request,HttpServletResponse response) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		List<Map<String, String>> list = userService.getRecUser();
		json.put("list", list);
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/inteToCoin", method = RequestMethod.POST)
	public void inteToCoin(HttpServletRequest request,HttpServletResponse response,
			String uid, Integer num) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		int status = userService.inteToCoin(uid, num);
		if(status < 1) {
			json.put("message", "�һ�ʧ�ܣ����Ժ�����");
		}
		this.writeJson(json.toString(), response);
	}

	@ModelAttribute
	@RequestMapping(value="/updAdmin", method = RequestMethod.POST)
	public void updAdmin(HttpServletRequest request,HttpServletResponse response,
			String uid, String userName, String phone, String img) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		UserDto user = userService.findUserByUUID(uid);
		String avatar = "";
		if(img.startsWith("data:image/jpeg;")) {
			String ImageName = this.getUUID() +".jpg";
			String path = context.getRealPath("/");
			String headImageUrl = path + "images\\" + ImageName ;
			try {
				if(Base64Utils.GenerateImage(img, headImageUrl)) {
					if(user.getAvatar() != null) {
						File file = new File(path +  user.getAvatar());
						if(file.exists() && file.isFile()){//�ж��ļ�Ŀ¼�Ƿ���ڣ��Ƿ����ļ�
							file.delete();
						}
					}
					avatar = "images/" + ImageName;
					user.setAvatar(avatar);
				}else {
					json.put("message", "ͼƬ�ϴ�����");
					this.writeJson(json.toString(), response);
					return ;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int updStatus = userService.updAdmin(uid, userName, phone, avatar);
		if(updStatus < 1) {
			json.put("message", "�޸�ʧ�ܣ����Ժ�����!");
			this.writeJson(json.toString(), response);
			return ;
		}
		user.setPassword("");
		json.put("user", user);
		this.writeJson(json.toString(), response);
	}

	@ModelAttribute
	@RequestMapping(value="/getAuthUser", method = RequestMethod.POST)
	public void getAuthUser(HttpServletRequest request,HttpServletResponse response, Integer page, String userName) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		Integer pages = (page-1) * 8;
		List<UserDto> userlist = userService.getAuthUser(pages, userName);
		json.put("userlist", userlist);
		
		int total = userService.getTotal();
		json.put("total", total);
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/updAuth", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED)
	public void updAuth(HttpServletRequest request,HttpServletResponse response, String uid, Integer pass, String reason) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		int authStatus = 0;
		if(pass == 1) {
			authStatus = userService.updAuth(uid);
		} else {
			authStatus = userService.updStatus(uid);
		}
		if(authStatus < 1) {
			json.put("message", "����ʧ�ܣ����Ժ�����");
			this.writeJson(json.toString(), response);
			return ;
		}
		
		String sysid = this.getUUID();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		int addSys = 0;
		if(pass == 1) {			
			addSys = sysNoticeService.addSysNotice(sysid, uid, "����ͨ����֤��", time);
		} else {
			addSys = sysNoticeService.addSysNotice(sysid, uid, reason, time);
		}
		if(addSys < 1) {
			json.put("message", "����ʧ�ܣ����Ժ�����");
		}
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/getUserByConditions", method = RequestMethod.POST)
	public void getUserByConditions(HttpServletRequest request,HttpServletResponse response, 
			Integer page, String userName, String type) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		Integer pages = (page-1) * 8;
		userName = "%" + userName + "%";
		List<UserDto> userList = userService.getUserByConditions(pages, userName, type);
		json.put("userList", userList);
		
		int total = userService.getTotalByType(type);
		json.put("total", total);
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/getUserDetailFromAdmin", method = RequestMethod.POST)
	public void getUserDetailFromAdmin(HttpServletRequest request,HttpServletResponse response, 
			String uid, String type) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		UserDto user = userService.findUserByUUID(uid);
		json.put("user", user);
		
		List<PartTimeJobDto> jobList = partjobService.findInfoByUID(uid);
		json.put("jobList", jobList);
		
		if(type == "��Ա") {
			List<Map<String, String>> evaList = evaluateService.getEvaListByUID(uid);
			json.put("evaList", evaList);
		}
		
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/reCoin", method = RequestMethod.POST)
	public void reCoin(HttpServletRequest request,HttpServletResponse response, 
			String uid) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		int coin = userService.ReCoin(uid);
		json.put("coin", coin);
		
		this.writeJson(json.toString(), response);
	}
	
}

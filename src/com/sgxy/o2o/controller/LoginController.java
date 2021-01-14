package com.sgxy.o2o.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.sgxy.o2o.dto.UserDto;
import com.sgxy.o2o.service.LoginService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/loginController")
public class LoginController extends BasicController{
	@Autowired
	@Qualifier("loginService")
	private LoginService loginService;

	@ModelAttribute
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public void login(HttpServletRequest request,HttpServletResponse response,String email,String password) {
		JSONObject json = new JSONObject();//Ҫ���ص�ҳ��Ķ���
		json.put("message", ""); // �ڶ������������һ������
		String md5Password=this.md5(password); // ��ԭʼ�������MD5���ܣ����ݿⱣ����Ǽ��ܺ������
		UserDto userDto = loginService.getUserByEmailAndPwd(email, md5Password);
		if(userDto == null) {
			json.put("message", "�û������������");
			this.writeJson(json.toString(), response);
			return;
		}else {
			userDto.setPassword("");
			json.put("user", userDto);
		}
		if(!userDto.getType().equals("����Ա")) {
			Map<String, String> logoutData = loginService.getLogout(userDto.getUserUUID());
			json.put("logout", logoutData);
		}
		this.writeJson(json.toString(), response);// �����ݷ��ص�ҳ��
	}
	
	@ModelAttribute
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public void register(HttpServletRequest request,HttpServletResponse response,String email,String password,
			String type, String phone) {
		JSONObject json = new JSONObject();//Ҫ���ص�ҳ��Ķ���
		json.put("message", ""); // �ڶ������������һ������
		UserDto isExistUser = loginService.getUserByEmail(email);
		if(isExistUser != null) {
			json.put("message", "�����Ѵ���");
			this.writeJson(json.toString(), response);
			return ;
		}
		String isExistPhone = loginService.getUserByPhone(phone);
		if(isExistPhone != null) {
			json.put("message", "�ֻ��ѱ�ע��");
			this.writeJson(json.toString(), response);
			return ;
		}
		String uid = this.getUUID();
		String md5Password = this.md5(password); // ��ԭʼ�������MD5���ܣ����ݿⱣ����Ǽ��ܺ������
		String userName = "�û�"+Math.floor(Math.random()*100000);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String regTime = df.format(new Date());
		int status = loginService.addUser(uid, email, md5Password, type, phone, userName, regTime, 0, "δ��֤");
		if(status < 1) {
			json.put("message", "ע��ʧ�ܣ����Ժ����ԣ�");
			this.writeJson(json.toString(), response);
		} else {
			UserDto user = new UserDto();
			user.setEmail(email);
			user.setPhone(phone);
			user.setUserName(userName);
			user.setType(type);
			user.setUserUUID(uid);
			user.setAvatar("images/default.jpg");
			json.put("user", user);
			this.writeJson(json.toString(), response);
		}
	}

	@ModelAttribute
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request,HttpServletResponse response,
			String uid,String chat, String sign) {
		JSONObject json = new JSONObject();//Ҫ���ص�ҳ��Ķ���
		json.put("message", ""); // �ڶ������������һ������
		String status = loginService.getLogoutData(uid);
		System.err.println(status);
		if(status != null) {
			System.err.println("ִ�и���");
			int updStatus = loginService.updData(chat, uid, sign);
			if(updStatus < 1) {
				json.put("message", "�˳�ʧ�ܣ����Ժ�����!");
			}
		} else {
			System.err.println("ִ�в���");
			String exid = this.getUUID();
			int addStatus = loginService.addLogout(exid, uid, chat, sign);
			if(addStatus < 1) {
				json.put("message", "�˳�ʧ�ܣ����Ժ�����!");
			}
		}
		
		this.writeJson(json.toString(), response);// �����ݷ��ص�ҳ��
	}
}

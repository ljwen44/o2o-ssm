package com.sgxy.o2o.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.sgxy.o2o.basic.controller.BasicController;
import com.sgxy.o2o.dto.BannerDto;
import com.sgxy.o2o.service.BannerService;
import com.sgxy.o2o.util.Base64Utils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/bannerController")
public class BannerController extends BasicController{
	@Autowired
	@Qualifier("bannerService")
	public BannerService bannerService;

	@Autowired
	ServletContext context;
	
	@ModelAttribute
	@RequestMapping(value="/getBanners", method = RequestMethod.POST)
	public void getBanners(HttpServletRequest request, HttpServletResponse response, Integer page) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		Integer pages = (page-1)*8;
		List<BannerDto> list = bannerService.getBanners(pages);
		json.put("list", list);
		
		int total = bannerService.getAll();
		json.put("total", total);
		
		this.writeJson(json.toString(), response);
	}

	@ModelAttribute
	@RequestMapping(value="/addBanner", method = RequestMethod.POST)
	public void addBanner(HttpServletRequest request, HttpServletResponse response, 
			String title, String desc, String img) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		String bid = this.getUUID();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String btime = df.format(new Date());
		String ImageName = this.getUUID() +".jpg";
		String path = context.getRealPath("/");
		String headImageUrl = path + "images\\" + ImageName ;
		String imgPath = "";
		try {
			if(Base64Utils.GenerateImage(img, headImageUrl)) {
				imgPath = "images/" + ImageName;
			}else {
				json.put("message", "图片上传出错");
				this.writeJson(json.toString(), response);
				return ;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		int addStatus = bannerService.addBanner(bid, imgPath, btime, title, desc);
		if(addStatus < 1) {
			json.put("message", "服务器错误，请刷新重试!");
		}
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/delBanner", method = RequestMethod.POST)
	public void delBanner(HttpServletRequest request, HttpServletResponse response, String bid, String img) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		int delStatus = bannerService.delBanner(bid);
		if(delStatus < 1) {
			json.put("message", "删除失败，请稍后重试");
			this.writeJson(json.toString(), response);
			return ;
		}
		String path = context.getRealPath("/");
		try {
			File file = new File(path + img);
			if(file.exists() && file.isFile()){//判断文件目录是否存在，是否是文件
				System.err.println("删除图片");
				file.delete();
			}
		} catch (Exception e) {
			System.err.println("删除失败!");
		}
		this.writeJson(json.toString(), response);
	}
	
	@ModelAttribute
	@RequestMapping(value="/editBanner", method = RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED)
	public void editBanner(HttpServletRequest request, HttpServletResponse response, String bid, String img,
			String title, String desc, String oimg) {
		JSONObject json = new JSONObject();
		json.put("message", "");
		
		System.err.println(bid);
		System.err.println(title);
		System.err.println(desc);
		System.err.println(img);
		
		String imgPath = "";
		if(img.startsWith("data:image/jpeg;")) {
			String ImageName = this.getUUID() +".jpg";
			String path = context.getRealPath("/");
			String ImageUrl = path + "images\\" + ImageName ;
			try {
				if(Base64Utils.GenerateImage(img, ImageUrl)) {
					File file = new File(path + oimg);
					if(file.exists() && file.isFile()){//判断文件目录是否存在，是否是文件
						System.err.println("删除图片");
						file.delete();
					}
					imgPath = "images/" + ImageName;
				}else {
					json.put("message", "图片上传出错");
					this.writeJson(json.toString(), response);
					return ;
				}
			} catch (IOException e) {
				json.put("message", "图片上传出错");
				this.writeJson(json.toString(), response);
				return ;
			}
		} else {
			imgPath = img;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String btime = df.format(new Date());
		int editStatus = bannerService.updBanner(bid, imgPath, btime, title, desc);
		if(editStatus < 1) {
			json.put("message", "修改失败，请稍后重试");
		}
		this.writeJson(json.toString(), response);
	}

}

package com.sgxy.o2o.dto;

public class BannerDto {
	public String bid; // 标识id
	public String img; // 图片
	public String btime; // 时间
	public String title; // 标题
	public String content; // 描述
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getBtime() {
		return btime;
	}
	public void setBtime(String btime) {
		this.btime = btime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setDesc(String content) {
		this.content = content;
	}
}

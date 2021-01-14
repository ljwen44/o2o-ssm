package com.sgxy.o2o.dto;

public class PartTimeJobDto {
	private String pid; // 标识id
	private String uid; // 用户标识id
	private String userName; // 联系人
	private String userPhone; // 联系电话
	private String province; // 省
	private String block; // 市
	private String city; // 区
	private String address; // 详细地址
	private String salary; // 薪资
	private String content; // 职位描述
	private String ptime; // 发布时间
	private String pstatus; // 家教状态
	private String precord; // 学历要求
	private int day; // 为期几天
	public String getpUUID() {
		return pid;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setpUUID(String pUUID) {
		this.pid = pUUID;
	}
	public String getUserUUID() {
		return uid;
	}
	public void setUserUUID(String userUUID) {
		this.uid = userUUID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMinSalary() {
		return salary;
	}
	public void setMinSalary(String salary) {
		this.salary = salary;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPtime() {
		return ptime;
	}
	public void setPtime(String ptime) {
		this.ptime = ptime;
	}
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}
	public String getPrecord() {
		return precord;
	}
	public void setPrecord(String precord) {
		this.precord = precord;
	}
	
}

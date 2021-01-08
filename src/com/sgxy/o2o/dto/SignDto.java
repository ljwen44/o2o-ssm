package com.sgxy.o2o.dto;

public class SignDto {
	private String sid; // 标识id
	private String uid; // 用户id
	private String sTime; // 签到时间
	public String getsUUID() {
		return sid;
	}
	public void setsUUID(String sUUID) {
		this.sid = sUUID;
	}
	public String getUserUUID() {
		return uid;
	}
	public void setUserUUID(String userUUID) {
		this.uid = userUUID;
	}
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	
}

package com.sgxy.o2o.dto;

public class SignDto {
	private String sid; // ��ʶid
	private String uid; // �û�id
	private String sTime; // ǩ��ʱ��
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

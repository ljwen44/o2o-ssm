package com.sgxy.o2o.dto;

public class AliPay {
	private String apid; // 标识id
	private String uid; // 用户id
	private String time; // 支付时间
	private String paynum; // 支付金额
	private int type; // 支付状态--> 0 表示支出，1表示收入
	public String getApid() {
		return apid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setApid(String apid) {
		this.apid = apid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPaynum() {
		return paynum;
	}
	public void setPaynum(String paynum) {
		this.paynum = paynum;
	}
	
}

package com.sgxy.o2o.dto;

public class AliPay {
	private String apid; // ��ʶid
	private String uid; // �û�id
	private String time; // ֧��ʱ��
	private String paynum; // ֧�����
	private int type; // ֧��״̬--> 0 ��ʾ֧����1��ʾ����
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

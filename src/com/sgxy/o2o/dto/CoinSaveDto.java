package com.sgxy.o2o.dto;

public class CoinSaveDto {
	private String csid; // ��ʶid
	private String pid; // ��ְ��ʶid
	private String uid; // �û���ʶid
	private int coin; // ѧ��
	private int flag; // �Ƿ���ת��
	private String time; // ������ɽ���ʱ��
	public String getCsid() {
		return csid;
	}
	public void setCsid(String csid) {
		this.csid = csid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}

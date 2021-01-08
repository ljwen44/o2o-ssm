package com.sgxy.o2o.dto;

public class CoinSaveDto {
	private String csid; // 标识id
	private String uid; // 用户标识id
	private String coin; // 学币
	private String flag; // 是否已转换
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
	public String getCoin() {
		return coin;
	}
	public void setCoin(String coin) {
		this.coin = coin;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}

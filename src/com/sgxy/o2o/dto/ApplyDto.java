package com.sgxy.o2o.dto;

public class ApplyDto {
	private String aid; // 标识id
	private String uid; // 用户id
	private String ruid; // 申请用户id
	private String flag; // 申请状态
	private String desc; // 申请备注
	private String atime; // 申请时间
	public String getAtime() {
		return atime;
	}
	public void setAtime(String atime) {
		this.atime = atime;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getRuid() {
		return ruid;
	}
	public void setRuid(String ruid) {
		this.ruid = ruid;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}

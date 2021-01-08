package com.sgxy.o2o.dto;

public class FriendDto {
	private String fid; // 标识id
	private String uid; // 用户id
	private String ruid; // 好友id
	private String ftime; // 添加时间
	public String getFtime() {
		return ftime;
	}
	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
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
	
}

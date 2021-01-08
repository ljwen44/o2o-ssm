package com.sgxy.o2o.dto;

public class ChatDto {
	private String cid; // 标识id
	private String uid; // 发送用户
	private String ruserUUID; // 接收用户
	private String message; // 消息内容
	private String ctime;  // 发送时间
	private String isRead; // 消息是否已读
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getRuserUUID() {
		return ruserUUID;
	}
	public void setRuserUUID(String ruserUUID) {
		this.ruserUUID = ruserUUID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
}

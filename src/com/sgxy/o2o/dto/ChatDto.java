package com.sgxy.o2o.dto;

public class ChatDto {
	private String cid; // 标识id
	private String uid; // 发送用户
	private String ruid; // 接收用户
	private String message; // 消息内容
	private String ctime;  // 发送时间
	private int isRead; // 消息是否已读
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
	public String getRUID() {
		return ruid;
	}
	public void setRUID(String ruid) {
		this.ruid = ruid;
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
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
}

package com.sgxy.o2o.dto;

public class ChatDto {
	private String cid; // ��ʶid
	private String uid; // �����û�
	private String ruid; // �����û�
	private String message; // ��Ϣ����
	private String ctime;  // ����ʱ��
	private int isRead; // ��Ϣ�Ƿ��Ѷ�
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

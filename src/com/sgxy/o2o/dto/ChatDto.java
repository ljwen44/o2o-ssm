package com.sgxy.o2o.dto;

public class ChatDto {
	private String cid; // ��ʶid
	private String uid; // �����û�
	private String ruserUUID; // �����û�
	private String message; // ��Ϣ����
	private String ctime;  // ����ʱ��
	private String isRead; // ��Ϣ�Ƿ��Ѷ�
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

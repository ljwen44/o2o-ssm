package com.sgxy.o2o.dto;

public class ProgressDto {
	private String ppid; // ��ʶid
	private String oid; // ����id
	private String content; // ��������
	private String ppTime; // ����ʱ��
	public String getPpUUID() {
		return ppid;
	}
	public void setPpUUID(String ppUUID) {
		this.ppid = ppUUID;
	}
	public String getOrderUUID() {
		return oid;
	}
	public void setOrderUUID(String orderUUID) {
		this.oid = orderUUID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPpTime() {
		return ppTime;
	}
	public void setPpTime(String ppTime) {
		this.ppTime = ppTime;
	}
	
}

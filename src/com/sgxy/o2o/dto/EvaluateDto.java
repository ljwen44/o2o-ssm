package com.sgxy.o2o.dto;

public class EvaluateDto {
	private String eid; // ��ʶid
	private String content; // ��������
	private String oid; // ����id
	private String uid; // �û���ʶid
	private String etime; // ����ʱ��
	private String erate; // �����Ǽ�
	private String ruid; // �����۵��û�
	public String getRuid() {
		return ruid;
	}
	public void setRuid(String ruid) {
		this.ruid = ruid;
	}
	public String getEUUID() {
		return eid;
	}
	public void setEUUID(String eUUID) {
		this.eid = eUUID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getErate() {
		return erate;
	}
	public void setErate(String erate) {
		this.erate = erate;
	}
	
}

package com.sgxy.o2o.dto;

public class PartTimeJobDto {
	private String pid; // ��ʶid
	private String uid; // �û���ʶid
	private String userName; // ��ϵ��
	private String userPhone; // ��ϵ�绰
	private String province; // ʡ
	private String block; // ��
	private String city; // ��
	private String address; // ��ϸ��ַ
	private String salary; // н��
	private String content; // ְλ����
	private String ptime; // ����ʱ��
	private String pstatus; // �ҽ�״̬
	private String precord; // ѧ��Ҫ��
	private int day; // Ϊ�ڼ���
	public String getpUUID() {
		return pid;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setpUUID(String pUUID) {
		this.pid = pUUID;
	}
	public String getUserUUID() {
		return uid;
	}
	public void setUserUUID(String userUUID) {
		this.uid = userUUID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMinSalary() {
		return salary;
	}
	public void setMinSalary(String salary) {
		this.salary = salary;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPtime() {
		return ptime;
	}
	public void setPtime(String ptime) {
		this.ptime = ptime;
	}
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}
	public String getPrecord() {
		return precord;
	}
	public void setPrecord(String precord) {
		this.precord = precord;
	}
	
}

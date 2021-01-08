package com.sgxy.o2o.dto;

public class UserDto {
	private String uid; // 标识id
	private String userName; // 用户名
	private String password; // 密码
	private String email; // 邮箱
	private String phone; // 手机号码
	private String sex; // 性别
	private String IDpass; // 证件
	private String introduce; // 简介
	private String school; // 毕业学校
	private String record; // 学历
	private String city; // 期望城市
	private String rate; // 评分
	private String auth; // 认证
	private String authStatus; // 认证状态
	private int intergral; // 积分
	private String graduation; // 毕业年限
	private String avatar; // 头像
	private String regTime; // 注册时间
	private int coin; // 学币
	private int credit; // 信用分
	private String type; // 类型
	private int calcIntegral; // 累计积分
	public String getType() {
		return type;
	}
	public int getCaclIntergral() {
		return calcIntegral;
	}
	public void setCaclIntergral(int calcIntegral) {
		this.calcIntegral = calcIntegral;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIDpass() {
		return IDpass;
	}
	public void setIDpass(String iDpass) {
		IDpass = iDpass;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	public int getIntergral() {
		return intergral;
	}
	public void setIntergral(int intergral) {
		this.intergral = intergral;
	}
	public String getGraduation() {
		return graduation;
	}
	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}

package com.sgxy.o2o.dto;

public class AlipayVo {
	public String out_biz_no;
	public String payee_type;
	public String amount; // 支付金额
	public String payee_account; // 支付账号
	public String payer_show_name; // 真实姓名
	public String payee_real_name; // 真实姓名
	public String remark; // 备注
	public String getOut_biz_no() {
		return out_biz_no;
	}
	public void setOut_biz_no(String out_biz_no) {
		this.out_biz_no = out_biz_no;
	}
	public String getPayee_type() {
		return payee_type;
	}
	public void setPayee_type(String payee_type) {
		this.payee_type = payee_type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPayee_account() {
		return payee_account;
	}
	public void setPayee_account(String payee_account) {
		this.payee_account = payee_account;
	}
	public String getPayer_show_name() {
		return payer_show_name;
	}
	public void setPayer_show_name(String payer_show_name) {
		this.payer_show_name = payer_show_name;
	}
	public String getPayee_real_name() {
		return payee_real_name;
	}
	public void setPayee_real_name(String payee_real_name) {
		this.payee_real_name = payee_real_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}

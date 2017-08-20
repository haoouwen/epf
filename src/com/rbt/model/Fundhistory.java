/*
  
 
 * Package:com.rbt.model
 * FileName: Fundhistory.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Fundhistory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private Double fund_in;
	public Double getFund_in() {
		return fund_in;
	}
	public void setFund_in(Double fund_in) {
		this.fund_in = fund_in;
	}
	
	private Double fund_out;
	public Double getFund_out() {
		return fund_out;
	}
	public void setFund_out(Double fund_out) {
		this.fund_out = fund_out;
	}
	
	private Double balance;
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String reason;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	private String action_type;
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	
	private String pay_type;
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fundhistory[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", fund_in=");
		builder.append(this.fund_in);
		builder.append(", fund_out=");
		builder.append(this.fund_out);
		builder.append(", balance=");
		builder.append(this.balance);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", reason=");
		builder.append(this.reason);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append(", action_type=");
		builder.append(this.action_type);
		builder.append(", pay_type=");
		builder.append(this.pay_type);
		builder.append("]");
		return builder.toString();
	}

}


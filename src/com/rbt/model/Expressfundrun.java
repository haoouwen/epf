/*
  
 
 * Package:com.rbt.model
 * FileName: Expressfundrun.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Expressfundrun implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String fundtype;
	public String getFundtype() {
		return fundtype;
	}
	public void setFundtype(String fundtype) {
		this.fundtype = fundtype;
	}
	
	private Double income;
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	
	private Double pay;
	public Double getPay() {
		return pay;
	}
	public void setPay(Double pay) {
		this.pay = pay;
	}
	
	private Double balance;
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String pay_code;
	public String getPay_code() {
		return pay_code;
	}
	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}
	
	private String deal_time;
	public String getDeal_time() {
		return deal_time;
	}
	public void setDeal_time(String deal_time) {
		this.deal_time = deal_time;
	}
	
	private String reason;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Expressfundrun[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", fundtype=");
		builder.append(this.fundtype);
		builder.append(", income=");
		builder.append(this.income);
		builder.append(", pay=");
		builder.append(this.pay);
		builder.append(", balance=");
		builder.append(this.balance);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", pay_code=");
		builder.append(this.pay_code);
		builder.append(", deal_time=");
		builder.append(this.deal_time);
		builder.append(", reason=");
		builder.append(this.reason);
		builder.append("]");
		return builder.toString();
	}

}


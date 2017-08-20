/*
  
 
 * Package:com.rbt.model
 * FileName: Fundrecharge.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Fundrecharge implements Serializable {

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
	
	private Double fund_num;
	public Double getFund_num() {
		return fund_num;
	}
	public void setFund_num(Double fund_num) {
		this.fund_num = fund_num;
	}
	
	private String payplat;
	public String getPayplat() {
		return payplat;
	}
	public void setPayplat(String payplat) {
		this.payplat = payplat;
	}
	
	private String pay_date;
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String order_state;
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	private String bank_order_id;
	public String getBank_order_id() {
		return bank_order_id;
	}
	public void setBank_order_id(String bank_order_id) {
		this.bank_order_id = bank_order_id;
	}
	private String order_id;
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String recharge_state;
	public String getRecharge_state() {
		return recharge_state;
	}
	public void setRecharge_state(String recharge_state) {
		this.recharge_state = recharge_state;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fundrecharge[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", fund_num=");
		builder.append(this.fund_num);
		builder.append(", payplat=");
		builder.append(this.payplat);
		builder.append(", pay_date=");
		builder.append(this.pay_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", order_state=");
		builder.append(this.order_state);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append(", bank_order_id=");
		builder.append(this.bank_order_id);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", recharge_state=");
		builder.append(this.recharge_state);
		builder.append("]");
		return builder.toString();
	}

}


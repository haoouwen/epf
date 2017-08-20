/*
  
 
 * Package:com.rbt.model
 * FileName: Onlinepaytrade.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Onlinepaytrade implements Serializable {

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
	
	private Double online_amount;
	public Double getOnline_amount() {
		return online_amount;
	}
	public void setOnline_amount(Double online_amount) {
		this.online_amount = online_amount;
	}
	
	private Double circlegold_amount;
	public Double getCirclegold_amount() {
		return circlegold_amount;
	}
	public void setCirclegold_amount(Double circlegold_amount) {
		this.circlegold_amount = circlegold_amount;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
		builder.append("Onlinepaytrade[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", online_amount=");
		builder.append(this.online_amount);
		builder.append(", circlegold_amount=");
		builder.append(this.circlegold_amount);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", state=");
		builder.append(this.state);
		builder.append(", pay_type=");
		builder.append(this.pay_type);
		builder.append("]");
		return builder.toString();
	}

}


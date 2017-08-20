/*
  
 
 * Package:com.rbt.model
 * FileName: Interhistory.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 积分流水信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Interhistory implements Serializable {

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
	
	private String inter_in;
	public String getInter_in() {
		return inter_in;
	}
	public void setInter_in(String inter_in) {
		this.inter_in = inter_in;
	}
	
	private String inter_out;
	public String getInter_out() {
		return inter_out;
	}
	public void setInter_out(String inter_out) {
		this.inter_out = inter_out;
	}
	
	private String thisinter;
	public String getThisinter() {
		return thisinter;
	}
	public void setThisinter(String thisinter) {
		this.thisinter = thisinter;
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

	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Interhistory[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", inter_in=");
		builder.append(this.inter_in);
		builder.append(", inter_out=");
		builder.append(this.inter_out);
		builder.append(", thisinter=");
		builder.append(this.thisinter);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", reason=");
		builder.append(this.reason);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append("]");
		return builder.toString();
	}

}


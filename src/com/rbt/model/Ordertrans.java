/*
  
 
 * Package:com.rbt.model
 * FileName: Ordertrans.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录商品订单异动信息实体
 * @author 创建人 HXK
 * @date 创建日期 Thu Feb 28 10:02:15 CST 2014
 */
public class Ordertrans implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trans_id;
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String order_state;
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String trans_time;
	public String getTrans_time() {
		return trans_time;
	}
	public void setTrans_time(String trans_time) {
		this.trans_time = trans_time;
	}
	
	private String reason;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	private String opt_username;
	
	public String getOpt_username() {
		return opt_username;
	}
	public void setOpt_username(String opt_username) {
		this.opt_username = opt_username;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ordertrans[");
		builder.append(", trans_id=");
		builder.append(this.trans_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", order_state=");
		builder.append(this.order_state);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", trans_time=");
		builder.append(this.trans_time);
		builder.append(", reason=");
		builder.append(this.reason);
		builder.append(", opt_username=");
		builder.append(this.opt_username);
		builder.append("]");
		return builder.toString();
	}

}


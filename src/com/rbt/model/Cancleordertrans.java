/*
  
 
 * Package:com.rbt.model
 * FileName: Cancleordertrans.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 取消申请进度实体
 * @author 创建人 XBY
 * @date 创建日期 Thu Sep 25 10:55:07 CST 2014
 */
public class Cancleordertrans implements Serializable {

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
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cancleordertrans[");
		builder.append(", trans_id=");
		builder.append(this.trans_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", trans_time=");
		builder.append(this.trans_time);
		builder.append(", reason=");
		builder.append(this.reason);
		builder.append("]");
		return builder.toString();
	}

}


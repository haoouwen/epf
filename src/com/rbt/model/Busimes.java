/*
  
 
 * Package:com.rbt.model
 * FileName: Busimes.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录商家留言信息实体
 * @author 创建人 CYC
 * @date 创建日期 Fri Mar 30 12:29:33 CST 2014
 */
public class Busimes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String get_cust_id;
	public String getGet_cust_id() {
		return get_cust_id;
	}
	public void setGet_cust_id(String get_cust_id) {
		this.get_cust_id = get_cust_id;
	}
	
	private String self_cust_id;
	public String getSelf_cust_id() {
		return self_cust_id;
	}
	public void setSelf_cust_id(String self_cust_id) {
		this.self_cust_id = self_cust_id;
	}
	
	private String self_user_id;
	public String getSelf_user_id() {
		return self_user_id;
	}
	public void setSelf_user_id(String self_user_id) {
		this.self_user_id = self_user_id;
	}
	
	private String get_user_id;
	public String getGet_user_id() {
		return get_user_id;
	}
	public void setGet_user_id(String get_user_id) {
		this.get_user_id = get_user_id;
	}
	
	private String msg_content;
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	
	private String msg_date;
	public String getMsg_date() {
		return msg_date;
	}
	public void setMsg_date(String msg_date) {
		this.msg_date = msg_date;
	}
	
	private String re_content;
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	
	private String re_date;
	public String getRe_date() {
		return re_date;
	}
	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}
	
	private String is_enable;
	public String getIs_enable() {
		return is_enable;
	}
	public void setIs_enable(String is_enable) {
		this.is_enable = is_enable;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Busimes[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", get_cust_id=");
		builder.append(this.get_cust_id);
		builder.append(", self_cust_id=");
		builder.append(this.self_cust_id);
		builder.append(", self_user_id=");
		builder.append(this.self_user_id);
		builder.append(", get_user_id=");
		builder.append(this.get_user_id);
		builder.append(", msg_content=");
		builder.append(this.msg_content);
		builder.append(", msg_date=");
		builder.append(this.msg_date);
		builder.append(", re_content=");
		builder.append(this.re_content);
		builder.append(", re_date=");
		builder.append(this.re_date);
		builder.append(", is_enable=");
		builder.append(this.is_enable);
		builder.append("]");
		return builder.toString();
	}

}


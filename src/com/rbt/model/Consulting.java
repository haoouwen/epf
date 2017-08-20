/*
  
 
 * Package:com.rbt.model
 * FileName: Consulting.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录商品咨询l回复信息实体
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 28 16:48:34 CST 2014
 */
public class Consulting implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String re_id;
	public String getRe_id() {
		return re_id;
	}
	public void setRe_id(String re_id) {
		this.re_id = re_id;
	}
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	
	private String is_display;
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Consulting[");
		builder.append(", re_id=");
		builder.append(this.re_id);
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", re_content=");
		builder.append(this.re_content);
		builder.append(", re_date=");
		builder.append(this.re_date);
		builder.append(", is_display=");
		builder.append(this.is_display);
		builder.append("]");
		return builder.toString();
	}

}


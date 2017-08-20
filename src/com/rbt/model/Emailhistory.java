/*
  
 
 * Package:com.rbt.model
 * FileName: Emailhistory.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Emailhistory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String get_email;
	public String getGet_email() {
		return get_email;
	}
	public void setGet_email(String get_email) {
		this.get_email = get_email;
	}
	
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	private String send_email;
	public String getSend_email() {
		return send_email;
	}
	public void setSend_email(String send_email) {
		this.send_email = send_email;
	}
	
	private String send_name;
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String temp_code;
	public String getTemp_code() {
		return temp_code;
	}
	public void setTemp_code(String temp_code) {
		this.temp_code = temp_code;
	}
	
	private String send_date;
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Emailhistory[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", get_email=");
		builder.append(this.get_email);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", send_email=");
		builder.append(this.send_email);
		builder.append(", send_name=");
		builder.append(this.send_name);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", temp_code=");
		builder.append(this.temp_code);
		builder.append(", send_date=");
		builder.append(this.send_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append("]");
		return builder.toString();
	}

}


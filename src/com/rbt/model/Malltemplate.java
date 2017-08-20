/*
  
 
 * Package:com.rbt.model
 * FileName: Malltemplate.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Malltemplate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String template_name;
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	
	private String template_code;
	public String getTemplate_code() {
		return template_code;
	}
	public void setTemplate_code(String template_code) {
		this.template_code = template_code;
	}
	
	private String template_path;
	public String getTemplate_path() {
		return template_path;
	}
	public void setTemplate_path(String template_path) {
		this.template_path = template_path;
	}
	
	private String template_image;
	public String getTemplate_image() {
		return template_image;
	}
	public void setTemplate_image(String template_image) {
		this.template_image = template_image;
	}
	
	private String author;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	
	private String is_enable;
	public String getIs_enable() {
		return is_enable;
	}
	public void setIs_enable(String is_enable) {
		this.is_enable = is_enable;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Malltemplate[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", template_name=");
		builder.append(this.template_name);
		builder.append(", template_code=");
		builder.append(this.template_code);
		builder.append(", template_path=");
		builder.append(this.template_path);
		builder.append(", template_image=");
		builder.append(this.template_image);
		builder.append(", author=");
		builder.append(this.author);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", is_enable=");
		builder.append(this.is_enable);
		builder.append("]");
		return builder.toString();
	}

}


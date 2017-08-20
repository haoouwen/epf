/*
  
 
 * Package:com.rbt.model
 * FileName: Template.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 瀑布流模板实体
 * @author 创建人 XBY
 * @date 创建日期 Wed Feb 19 17:12:59 CST 2014
 */
public class Template implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String template_id;
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	
	private String template_name;
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	
	private String width;
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
	private String height;
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
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
	
	private String template_content;
	public String getTemplate_content() {
		return template_content;
	}
	public void setTemplate_content(String template_content) {
		this.template_content = template_content;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Template[");
		builder.append(", template_id=");
		builder.append(this.template_id);
		builder.append(", template_name=");
		builder.append(this.template_name);
		builder.append(", width=");
		builder.append(this.width);
		builder.append(", height=");
		builder.append(this.height);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", template_content=");
		builder.append(this.template_content);
		builder.append("]");
		return builder.toString();
	}

}


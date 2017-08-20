/*
  
 
 * Package:com.rbt.model
 * FileName: Waterfall.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 瀑布流实体实体
 * @author 创建人 XBY
 * @date 创建日期 Thu Jul 25 13:57:35 CST 2014
 */
public class Waterfall implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String wf_code;
	public String getWf_code() {
		return wf_code;
	}
	public void setWf_code(String wf_code) {
		this.wf_code = wf_code;
	}
	
	private String template_content;
	public String getTemplate_content() {
		return template_content;
	}
	public void setTemplate_content(String template_content) {
		this.template_content = template_content;
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
	
	private String temp_type;
	public String getTemp_type() {
		return temp_type;
	}
	public void setTemp_type(String temp_type) {
		this.temp_type = temp_type;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Waterfall[");
		builder.append(", wf_code=");
		builder.append(this.wf_code);
		builder.append(", template_content=");
		builder.append(this.template_content);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", width=");
		builder.append(this.width);
		builder.append(", height=");
		builder.append(this.height);
		builder.append(", temp_type=");
		builder.append(this.temp_type);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append("]");
		return builder.toString();
	}

}


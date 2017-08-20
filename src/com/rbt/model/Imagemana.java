/*
  
 
 * Package:com.rbt.model
 * FileName: Imagemana.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Imagemana implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String serial_id;
	public String getSerial_id() {
		return serial_id;
	}
	public void setSerial_id(String serial_id) {
		this.serial_id = serial_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	private String note;
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Imagemana[");
		builder.append(", serial_id=");
		builder.append(this.serial_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", note=");
		builder.append(this.note);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", type=");
		builder.append(this.type);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append("]");
		return builder.toString();
	}

}


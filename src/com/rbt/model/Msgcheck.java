/*
  
 
 * Package:com.rbt.model
 * FileName: Msgcheck.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Msgcheck implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	private String cp_phone;
	public String getCp_phone() {
		return cp_phone;
	}
	public void setCp_phone(String cp_phone) {
		this.cp_phone = cp_phone;
	}
	
	private String cp_check;
	public String getCp_check() {
		return cp_check;
	}
	public void setCp_check(String cp_check) {
		this.cp_check = cp_check;
	}
	
	private String send_time;
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	
	private String cp_type;
	public String getCp_type() {
		return cp_type;
	}
	public void setCp_type(String cp_type) {
		this.cp_type = cp_type;
	}
	
	private String mark_id;
	public String getMark_id() {
		return mark_id;
	}
	public void setMark_id(String mark_id) {
		this.mark_id = mark_id;
	}
	private String cp_use;
	public String getCp_use() {
		return cp_use;
	}
	public void setCp_use(String cp_use) {
		this.cp_use = cp_use;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Msgcheck[");
		builder.append(", id=");
		builder.append(this.id);
		builder.append(", cp_phone=");
		builder.append(this.cp_phone);
		builder.append(", cp_check=");
		builder.append(this.cp_check);
		builder.append(", send_time=");
		builder.append(this.send_time);
		builder.append(", cp_type=");
		builder.append(this.cp_type);
		builder.append(", mark_id=");
		builder.append(this.mark_id);
		builder.append(", cp_use=");
		builder.append(this.cp_use);
		builder.append("]");
		return builder.toString();
	}

}


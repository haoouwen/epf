/*
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Depot.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 仓库信息实体
 * @author 创建人 ZMS
 * @date 创建日期 Wed Aug 05 20:27:34 CST 2015
 */
public class Depot implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String depot_id;
	public String getDepot_id() {
		return depot_id;
	}
	public void setDepot_id(String depot_id) {
		this.depot_id = depot_id;
	}
	
	private String depot_name;
	public String getDepot_name() {
		return depot_name;
	}
	public void setDepot_name(String depot_name) {
		this.depot_name = depot_name;
	}
	
	private String depot_add;
	public String getDepot_add() {
		return depot_add;
	}
	public void setDepot_add(String depot_add) {
		this.depot_add = depot_add;
	}
	
	private String depot_mail;
	public String getDepot_mail() {
		return depot_mail;
	}
	public void setDepot_mail(String depot_mail) {
		this.depot_mail = depot_mail;
	}
	
	private String contact;
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private String is_sys;
	
	public String getIs_sys() {
		return is_sys;
	}
	public void setIs_sys(String is_sys) {
		this.is_sys = is_sys;
	}
	private String is_international;
	
	public String getIs_international() {
		return is_international;
	}
	public void setIs_international(String is_international) {
		this.is_international = is_international;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Depot[");
		builder.append(", depot_id=");
		builder.append(this.depot_id);
		builder.append(", depot_name=");
		builder.append(this.depot_name);
		builder.append(", depot_add=");
		builder.append(this.depot_add);
		builder.append(", depot_mail=");
		builder.append(this.depot_mail);
		builder.append(", contact=");
		builder.append(this.contact);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", is_sys=");
		builder.append(this.is_sys);
		builder.append(", is_international=");
		builder.append(this.is_international);
		builder.append("]");
		return builder.toString();
	}

}


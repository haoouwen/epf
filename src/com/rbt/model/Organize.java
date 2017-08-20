/*
  
 
 * Package:com.rbt.model
 * FileName: Organize.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Organize implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String org_id;
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	
	private String org_name;
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	
	private String up_org_id;
	public String getUp_org_id() {
		return up_org_id;
	}
	public void setUp_org_id(String up_org_id) {
		this.up_org_id = up_org_id;
	}
	
	private String org_level;
	public String getOrg_level() {
		return org_level;
	}
	public void setOrg_level(String org_level) {
		this.org_level = org_level;
	}
	
	private String sys_org;
	public String getSys_org() {
		return sys_org;
	}
	public void setSys_org(String sys_org) {
		this.sys_org = sys_org;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
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
	
	private String cellphone;
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	private String skype;
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	
	private String qq;
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	private String msn;
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private String fax;
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Organize[");
		builder.append(", org_id=");
		builder.append(this.org_id);
		builder.append(", org_name=");
		builder.append(this.org_name);
		builder.append(", up_org_id=");
		builder.append(this.up_org_id);
		builder.append(", org_level=");
		builder.append(this.org_level);
		builder.append(", sys_org=");
		builder.append(this.sys_org);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", contact=");
		builder.append(this.contact);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", cellphone=");
		builder.append(this.cellphone);
		builder.append(", skype=");
		builder.append(this.skype);
		builder.append(", qq=");
		builder.append(this.qq);
		builder.append(", msn=");
		builder.append(this.msn);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", fax=");
		builder.append(this.fax);
		builder.append(", address=");
		builder.append(this.address);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append("]");
		return builder.toString();
	}

}


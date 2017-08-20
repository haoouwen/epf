/*
  
 
 * Package:com.rbt.model
 * FileName: Certification.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:21 CST 2014
 */
public class Certification implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String reason;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	private String cust_name;
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	private String corporate;
	public String getCorporate() {
		return corporate;
	}
	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}
	
	private String cust_type;
	public String getCust_type() {
		return cust_type;
	}
	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}
	
	private String reg_money;
	public String getReg_money() {
		return reg_money;
	}
	public void setReg_money(String reg_money) {
		this.reg_money = reg_money;
	}
	
	private String currency;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	private String o_start_date;
	public String getO_start_date() {
		return o_start_date;
	}
	public void setO_start_date(String o_start_date) {
		this.o_start_date = o_start_date;
	}
	
	private String o_end_date;
	public String getO_end_date() {
		return o_end_date;
	}
	public void setO_end_date(String o_end_date) {
		this.o_end_date = o_end_date;
	}
	
	private String class_attr;
	public String getClass_attr() {
		return class_attr;
	}
	public void setClass_attr(String class_attr) {
		this.class_attr = class_attr;
	}
	
	private String reg_date;
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	private String is_inspect;
	public String getIs_inspect() {
		return is_inspect;
	}
	public void setIs_inspect(String is_inspect) {
		this.is_inspect = is_inspect;
	}
	
	private String ins_record;
	public String getIns_record() {
		return ins_record;
	}
	public void setIns_record(String ins_record) {
		this.ins_record = ins_record;
	}
	
	private String reg_auth;
	public String getReg_auth() {
		return reg_auth;
	}
	public void setReg_auth(String reg_auth) {
		this.reg_auth = reg_auth;
	}
	
	private String license_path;
	public String getLicense_path() {
		return license_path;
	}
	public void setLicense_path(String license_path) {
		this.license_path = license_path;
	}
	
	private String app_name;
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	
	private String app_depart;
	public String getApp_depart() {
		return app_depart;
	}
	public void setApp_depart(String app_depart) {
		this.app_depart = app_depart;
	}
	
	private String app_career;
	public String getApp_career() {
		return app_career;
	}
	public void setApp_career(String app_career) {
		this.app_career = app_career;
	}
	
	private String app_contact;
	public String getApp_contact() {
		return app_contact;
	}
	public void setApp_contact(String app_contact) {
		this.app_contact = app_contact;
	}
	
	private String auth_path;
	public String getAuth_path() {
		return auth_path;
	}
	public void setAuth_path(String auth_path) {
		this.auth_path = auth_path;
	}
	
	private String audit_date;
	public String getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(String audit_date) {
		this.audit_date = audit_date;
	}
	
	private String audit_user_id;
	public String getAudit_user_id() {
		return audit_user_id;
	}
	public void setAudit_user_id(String audit_user_id) {
		this.audit_user_id = audit_user_id;
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
		builder.append("Certification[");
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", reason=");
		builder.append(this.reason);
		builder.append(", cust_name=");
		builder.append(this.cust_name);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", address=");
		builder.append(this.address);
		builder.append(", corporate=");
		builder.append(this.corporate);
		builder.append(", cust_type=");
		builder.append(this.cust_type);
		builder.append(", reg_money=");
		builder.append(this.reg_money);
		builder.append(", currency=");
		builder.append(this.currency);
		builder.append(", o_start_date=");
		builder.append(this.o_start_date);
		builder.append(", o_end_date=");
		builder.append(this.o_end_date);
		builder.append(", class_attr=");
		builder.append(this.class_attr);
		builder.append(", reg_date=");
		builder.append(this.reg_date);
		builder.append(", is_inspect=");
		builder.append(this.is_inspect);
		builder.append(", ins_record=");
		builder.append(this.ins_record);
		builder.append(", reg_auth=");
		builder.append(this.reg_auth);
		builder.append(", license_path=");
		builder.append(this.license_path);
		builder.append(", app_name=");
		builder.append(this.app_name);
		builder.append(", app_depart=");
		builder.append(this.app_depart);
		builder.append(", app_career=");
		builder.append(this.app_career);
		builder.append(", app_contact=");
		builder.append(this.app_contact);
		builder.append(", auth_path=");
		builder.append(this.auth_path);
		builder.append(", audit_date=");
		builder.append(this.audit_date);
		builder.append(", audit_user_id=");
		builder.append(this.audit_user_id);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append("]");
		return builder.toString();
	}

}


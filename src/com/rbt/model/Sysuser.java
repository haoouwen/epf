/*
  
 
 * Package:com.rbt.model
 * FileName: Sysuser.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Sysuser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String user_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	private String nike_name;
	public String getNike_name() {
		return nike_name;
	}
	public void setNike_name(String nike_name) {
		this.nike_name = nike_name;
	}
	
	private String passwd;
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	private String user_type;
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	private String role_id;
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	private String real_name;
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private String loginnum;
	public String getLoginnum() {
		return loginnum;
	}
	public void setLoginnum(String loginnum) {
		this.loginnum = loginnum;
	}
	
	private String logintime;
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	
	private String loginip;
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	private String org_id;
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	
	private String grade;
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	private String subjection;
	public String getSubjection() {
		return subjection;
	}
	public void setSubjection(String subjection) {
		this.subjection = subjection;
	}
	private String is_del;
	
	public String getIs_del() {
		return is_del;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sysuser[");
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", user_name=");
		builder.append(this.user_name);
		builder.append(", nike_name=");
		builder.append(this.nike_name);
		builder.append(", passwd=");
		builder.append(this.passwd);
		builder.append(", user_type=");
		builder.append(this.user_type);
		builder.append(", role_id=");
		builder.append(this.role_id);
		builder.append(", real_name=");
		builder.append(this.real_name);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", loginnum=");
		builder.append(this.loginnum);
		builder.append(", logintime=");
		builder.append(this.logintime);
		builder.append(", loginip=");
		builder.append(this.loginip);
		builder.append(", state=");
		builder.append(this.state);
		builder.append(", org_id=");
		builder.append(this.org_id);
		builder.append(", is_del=");
		builder.append(this.is_del);
		builder.append("]");
		return builder.toString();
	}

}


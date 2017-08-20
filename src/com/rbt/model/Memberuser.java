/*
  
 
 * Package:com.rbt.model
 * FileName: Memberuser.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Memberuser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String user_type;
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	private String user_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	private String passwd;
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	private String role_code;
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private String real_name;
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	private String sex;
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	private String cellphone;
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	private String skype;
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	
	private String pass_strength;
	public String getPass_strength() {
		return pass_strength;
	}
	public void setPass_strength(String pass_strength) {
		this.pass_strength = pass_strength;
	}
	
	private String login_time;
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	
	private String login_count;
	public String getLogin_count() {
		return login_count;
	}
	public void setLogin_count(String login_count) {
		this.login_count = login_count;
	}
	
	private String login_ip;
	public String getLogin_ip() {
		return login_ip;
	}
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	
	private String is_check_mobile;
	public String getIs_check_mobile() {
		return is_check_mobile;
	}
	public void setIs_check_mobile(String is_check_mobile) {
		this.is_check_mobile = is_check_mobile;
	}
	private String is_check_email;
	public String getIs_check_email() {
		return is_check_email;
	}
	public void setIs_check_email(String is_check_email) {
		this.is_check_email = is_check_email;
	}
	
	private String loginapiid;
	public String getLoginapiid() {
		return loginapiid;
	}
	public void setLoginapiid(String loginapiid) {
		this.loginapiid = loginapiid;
	}
	private String birthday;
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	private String open_id;
	public String getOpen_id() {
		return open_id;
	}
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	
	private String membernum;
	public String getMembernum() {
		return membernum;
	}
	public void setMembernum(String membernum) {
		this.membernum = membernum;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memberuser[");
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", user_type=");
		builder.append(this.user_type);
		builder.append(", user_name=");
		builder.append(this.user_name);
		builder.append(", passwd=");
		builder.append(this.passwd);
		builder.append(", role_code=");
		builder.append(this.role_code);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", real_name=");
		builder.append(this.real_name);
		builder.append(", sex=");
		builder.append(this.sex);
		builder.append(", cellphone=");
		builder.append(this.cellphone);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", qq=");
		builder.append(this.qq);
		builder.append(", msn=");
		builder.append(this.msn);
		builder.append(", skype=");
		builder.append(this.skype);
		builder.append(", pass_strength=");
		builder.append(this.pass_strength);
		builder.append(", login_time=");
		builder.append(this.login_time);
		builder.append(", login_count=");
		builder.append(this.login_count);
		builder.append(", login_ip=");
		builder.append(this.login_ip);
		builder.append(", is_check_mobile=");
		builder.append(this.is_check_mobile);
		builder.append(", is_check_email=");
		builder.append(this.is_check_email);
		builder.append(", birthday=");
		builder.append(this.birthday);
		builder.append(", open_id=");
		builder.append(this.open_id);
		builder.append(", membernum=");
		builder.append(this.membernum);
		builder.append("]");
		return builder.toString();
	}

}


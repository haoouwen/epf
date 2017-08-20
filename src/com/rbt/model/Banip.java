/*
  
 
 * Package:com.rbt.model
 * FileName: Banip.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:21 CST 2014
 */
public class Banip implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String ip_id;
	public String getIp_id() {
		return ip_id;
	}
	public void setIp_id(String ip_id) {
		this.ip_id = ip_id;
	}
	
	private String ip;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Banip[");
		builder.append(", ip_id=");
		builder.append(this.ip_id);
		builder.append(", ip=");
		builder.append(this.ip);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}


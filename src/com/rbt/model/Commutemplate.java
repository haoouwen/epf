/*
  
 
 * Package:com.rbt.model
 * FileName: Commutemplate.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Commutemplate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String temp_id;
	public String getTemp_id() {
		return temp_id;
	}
	public void setTemp_id(String temp_id) {
		this.temp_id = temp_id;
	}
	
	private String temp_name;
	public String getTemp_name() {
		return temp_name;
	}
	public void setTemp_name(String temp_name) {
		this.temp_name = temp_name;
	}
	
	private String temp_con;
	public String getTemp_con() {
		return temp_con;
	}
	public void setTemp_con(String temp_con) {
		this.temp_con = temp_con;
	}
	
	private String tag_desc;
	public String getTag_desc() {
		return tag_desc;
	}
	public void setTag_desc(String tag_desc) {
		this.tag_desc = tag_desc;
	}
	
	private String temp_code;
	public String getTemp_code() {
		return temp_code;
	}
	public void setTemp_code(String temp_code) {
		this.temp_code = temp_code;
	}
	
	private String sys_temp;
	public String getSys_temp() {
		return sys_temp;
	}
	public void setSys_temp(String sys_temp) {
		this.sys_temp = sys_temp;
	}
	
	private String temp_type;
	public String getTemp_type() {
		return temp_type;
	}
	public void setTemp_type(String temp_type) {
		this.temp_type = temp_type;
	}
	
	
	private String send_who;
	/**
	 * @author:HXM
	 * @date:Jun 4, 20147:21:08 PM
	 * @param:
	 * @Description: 1 发给买家,2 发给卖家,3 发给双方
	 */
	public String getSend_who() {
		return send_who;
	}
	public void setSend_who(String send_who) {
		this.send_who = send_who;
	}
	
	private String temp_conTwo;
	public String getTemp_conTwo() {
		return temp_conTwo;
	}
	public void setTemp_conTwo(String temp_conTwo) {
		this.temp_conTwo = temp_conTwo;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Commutemplate[");
		builder.append(", temp_id=");
		builder.append(this.temp_id);
		builder.append(", temp_name=");
		builder.append(this.temp_name);
		builder.append(", temp_con=");
		builder.append(this.temp_con);
		builder.append(", tag_desc=");
		builder.append(this.tag_desc);
		builder.append(", temp_code=");
		builder.append(this.temp_code);
		builder.append(", sys_temp=");
		builder.append(this.sys_temp);
		builder.append(", temp_type=");
		builder.append(this.temp_type);
		builder.append(", send_who=");
		builder.append(this.send_who);
		builder.append(", temp_conTwo=");
		builder.append(this.temp_conTwo);
		builder.append("]");
		return builder.toString();
	}

}


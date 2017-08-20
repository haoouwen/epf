/*
  
 
 * Package:com.rbt.model
 * FileName: Memrole.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Memrole implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String memrole_id;
	public String getMemrole_id() {
		return memrole_id;
	}
	public void setMemrole_id(String memrole_id) {
		this.memrole_id = memrole_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	private String oper_right;
	private String memrole_name;
	public String getMemrole_name() {
		return memrole_name;
	}
	public void setMemrole_name(String memrole_name) {
		this.memrole_name = memrole_name;
	}
	private String memmenu_right;
	public String getMemmenu_right() {
		return memmenu_right;
	}
	public void setMemmenu_right(String memmenu_right) {
		this.memmenu_right = memmenu_right;
	}
	
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memrole[");
		builder.append(", memrole_id=");
		builder.append(this.memrole_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", memrole_name=");
		builder.append(this.memrole_name);
		builder.append(", memmenu_right=");
		builder.append(this.memmenu_right);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append("]");
		return builder.toString();
	}
	public String getOper_right() {
		return oper_right;
	}
	public void setOper_right(String oper_right) {
		this.oper_right = oper_right;
	}
	
}


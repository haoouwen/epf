/*
  
 
 * Package:com.rbt.model
 * FileName: Role.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String role_id;
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String role_name;
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	private String menu_right;
	public String getMenu_right() {
		return menu_right;
	}
	public void setMenu_right(String menu_right) {
		this.menu_right = menu_right;
	}
	
	private String oper_right;
	public String getOper_right() {
		return oper_right;
	}
	public void setOper_right(String oper_right) {
		this.oper_right = oper_right;
	}
	
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setuser_id(String user_id) {
		this.user_id = user_id;
	}
	private String is_system;
	
	public String getIs_system() {
		return is_system;
	}
	public void setIs_system(String is_system) {
		this.is_system = is_system;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role[");
		builder.append(", role_id=");
		builder.append(this.role_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", role_name=");
		builder.append(this.role_name);
		builder.append(", menu_right=");
		builder.append(this.menu_right);
		builder.append(", oper_right=");
		builder.append(this.oper_right);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append(", is_system=");
		builder.append(this.is_system);
		builder.append("]");
		return builder.toString();
	}

}


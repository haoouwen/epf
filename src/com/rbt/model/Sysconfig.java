/*
  
 
 * Package:com.rbt.model
 * FileName: Sysconfig.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Sysconfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String var_id;
	public String getVar_id() {
		return var_id;
	}
	public void setVar_id(String var_id) {
		this.var_id = var_id;
	}
	
	private String var_name;
	public String getVar_name() {
		return var_name;
	}
	public void setVar_name(String var_name) {
		this.var_name = var_name;
	}
	
	private String var_value;
	public String getVar_value() {
		return var_value;
	}
	public void setVar_value(String var_value) {
		this.var_value = var_value;
	}
	
	private String var_desc;
	public String getVar_desc() {
		return var_desc;
	}
	public void setVar_desc(String var_desc) {
		this.var_desc = var_desc;
	}
	
	private String var_group;
	public String getVar_group() {
		return var_group;
	}
	public void setVar_group(String var_group) {
		this.var_group = var_group;
	}
	
	private String var_type;
	public String getVar_type() {
		return var_type;
	}
	public void setVar_type(String var_type) {
		this.var_type = var_type;
	}
	
	private String val_sys;
	public String getVal_sys() {
		return val_sys;
	}
	public void setVal_sys(String val_sys) {
		this.val_sys = val_sys;
	}
	
	private String module_type;
	public String getModule_type() {
		return module_type;
	}
	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sysconfig[");
		builder.append(", var_id=");
		builder.append(this.var_id);
		builder.append(", var_name=");
		builder.append(this.var_name);
		builder.append(", var_value=");
		builder.append(this.var_value);
		builder.append(", var_desc=");
		builder.append(this.var_desc);
		builder.append(", var_group=");
		builder.append(this.var_group);
		builder.append(", var_type=");
		builder.append(this.var_type);
		builder.append(", val_sys=");
		builder.append(this.val_sys);
		builder.append(", module_type=");
		builder.append(this.module_type);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append("]");
		return builder.toString();
	}

}


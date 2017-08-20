/*
  
 
 * Package:com.rbt.model
 * FileName: Paravalue.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Paravalue implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String para_id;
	public String getPara_id() {
		return para_id;
	}
	public void setPara_id(String para_id) {
		this.para_id = para_id;
	}
	
	private String para_group_id;
	public String getPara_group_id() {
		return para_group_id;
	}
	public void setPara_group_id(String para_group_id) {
		this.para_group_id = para_group_id;
	}
	
	private String para_name;
	public String getPara_name() {
		return para_name;
	}
	public void setPara_name(String para_name) {
		this.para_name = para_name;
	}
	
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
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
		builder.append("Paravalue[");
		builder.append(", para_id=");
		builder.append(this.para_id);
		builder.append(", para_group_id=");
		builder.append(this.para_group_id);
		builder.append(", para_name=");
		builder.append(this.para_name);
		builder.append(", value=");
		builder.append(this.value);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}


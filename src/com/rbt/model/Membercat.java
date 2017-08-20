/*
  
 
 * Package:com.rbt.model
 * FileName: Membercat.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Membercat implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String cat_id;
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String cat_name;
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	
	private String cat_type;
	public String getCat_type() {
		return cat_type;
	}
	public void setCat_type(String cat_type) {
		this.cat_type = cat_type;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String parent_cat_id;
	public String getParent_cat_id() {
		return parent_cat_id;
	}
	public void setParent_cat_id(String parent_cat_id) {
		this.parent_cat_id = parent_cat_id;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	private String level;
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Membercat[");
		builder.append(", cat_id=");
		builder.append(this.cat_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", cat_name=");
		builder.append(this.cat_name);
		builder.append(", cat_type=");
		builder.append(this.cat_type);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", parent_cat_id=");
		builder.append(this.parent_cat_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", level=");
		builder.append(this.level);
		builder.append("]");
		return builder.toString();
	}

}


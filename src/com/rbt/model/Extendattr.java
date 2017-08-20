/*
  
 
 * Package:com.rbt.model
 * FileName: Extendattr.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Extendattr implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String in_date;
	private String ex_attr_id;
	public String getEx_attr_id() {
		return ex_attr_id;
	}
	public void setEx_attr_id(String ex_attr_id) {
		this.ex_attr_id = ex_attr_id;
	}
	
	private String attr_name;

	
	private String option_type;
	public String getOption_type() {
		return option_type;
	}
	public void setOption_type(String option_type) {
		this.option_type = option_type;
	}
	
	private String default_value;
	public String getDefault_value() {
		return default_value;
	}
	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}
	
	private String is_display;
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Extendattr[");
		builder.append(", ex_attr_id=");
		builder.append(this.ex_attr_id);
		builder.append(", attr_code=");
		builder.append(", option_type=");
		builder.append(this.option_type);
		builder.append(", default_value=");
		builder.append(this.default_value);
		builder.append(", is_display=");
		builder.append(this.is_display);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}
	public String getAttr_name() {
		return attr_name;
	}
	public void setAttr_name(String attr_name) {
		this.attr_name = attr_name;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

}


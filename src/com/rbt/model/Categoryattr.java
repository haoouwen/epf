/*
  
 
 * Package:com.rbt.model
 * FileName: Categoryattr.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:21 CST 2014
 */
public class Categoryattr implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String attr_id;
	public String getAttr_id() {
		return attr_id;
	}
	public void setAttr_id(String attr_id) {
		this.attr_id = attr_id;
	}
	
	private String attr_name;
	public String getAttr_name() {
		return attr_name;
	}
	public void setAttr_name(String attr_name) {
		this.attr_name = attr_name;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private String attr_type;
	public String getAttr_type() {
		return attr_type;
	}
	public void setAttr_type(String attr_type) {
		this.attr_type = attr_type;
	}
	
	private String is_must;
	public String getIs_must() {
		return is_must;
	}
	public void setIs_must(String is_must) {
		this.is_must = is_must;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String module_type;
	public String getModule_type() {
		return module_type;
	}
	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	
	private String default_val;
	public String getDefault_val() {
		return default_val;
	}
	public void setDefault_val(String default_val) {
		this.default_val = default_val;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categoryattr[");
		builder.append(", attr_id=");
		builder.append(this.attr_id);
		builder.append(", attr_name=");
		builder.append(this.attr_name);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", attr_type=");
		builder.append(this.attr_type);
		builder.append(", is_must=");
		builder.append(this.is_must);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", module_type=");
		builder.append(this.module_type);
		builder.append(", default_val=");
		builder.append(this.default_val);
		builder.append("]");
		return builder.toString();
	}

}


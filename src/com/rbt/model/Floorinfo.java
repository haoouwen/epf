/*
 
 * Package:com.rbt.model
 * FileName: Floorinfo.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;

import com.hp.hpl.sparta.xpath.ThisNodeTest;
/**
 * @function 功能 楼层管理实体
 * @author 创建人 HXK
 * @date 创建日期 Sat Aug 08 16:33:52 CST 2015
 */
public class Floorinfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String f_id;
	public String getF_id() {
		return f_id;
	}
	public void setF_id(String f_id) {
		this.f_id = f_id;
	}
	
	private String f_tag;
	public String getF_tag() {
		return f_tag;
	}
	public void setF_tag(String f_tag) {
		this.f_tag = f_tag;
	}
	
	private String f_name;
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private String f_admark;
	public String getF_admark() {
		return f_admark;
	}
	public void setF_admark(String f_admark) {
		this.f_admark = f_admark;
	}
	
	private String f_sort;
	public String getF_sort() {
		return f_sort;
	}
	public void setF_sort(String f_sort) {
		this.f_sort = f_sort;
	}
	
	private String f_state;
	public String getF_state() {
		return f_state;
	}
	public void setF_state(String f_state) {
		this.f_state = f_state;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String is_sys;
	public String getIs_sys() {
		return is_sys;
	}
	public void setIs_sys(String is_sys) {
		this.is_sys = is_sys;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Floorinfo[");
		builder.append(", f_id=");
		builder.append(this.f_id);
		builder.append(", f_tag=");
		builder.append(this.f_tag);
		builder.append(", f_name=");
		builder.append(this.f_name);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", f_admark=");
		builder.append(this.f_admark);
		builder.append(", f_sort=");
		builder.append(this.f_sort);
		builder.append(", f_state=");
		builder.append(this.f_state);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", is_sys=");
		builder.append(this.is_sys);
		builder.append("]");
		return builder.toString();
	}

}


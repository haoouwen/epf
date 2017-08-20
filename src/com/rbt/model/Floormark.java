/*
 
 * Package:com.rbt.model
 * FileName: Floormark.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 楼层标签实体
 * @author 创建人 HXK
 * @date 创建日期 Sat Aug 08 16:35:30 CST 2015
 */
public class Floormark implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String fm_id;
	public String getFm_id() {
		return fm_id;
	}
	public void setFm_id(String fm_id) {
		this.fm_id = fm_id;
	}
	
	private String fm_name;
	public String getFm_name() {
		return fm_name;
	}
	public void setFm_name(String fm_name) {
		this.fm_name = fm_name;
	}
	
	private String fm_remark;
	public String getFm_remark() {
		return fm_remark;
	}
	public void setFm_remark(String fm_remark) {
		this.fm_remark = fm_remark;
	}
	
	private String fm_sort;
	public String getFm_sort() {
		return fm_sort;
	}
	public void setFm_sort(String fm_sort) {
		this.fm_sort = fm_sort;
	}
	
	private String f_id;
	public String getF_id() {
		return f_id;
	}
	public void setF_id(String f_id) {
		this.f_id = f_id;
	}
	
	private String fm_type;
	public String getFm_type() {
		return fm_type;
	}
	public void setFm_type(String fm_type) {
		this.fm_type = fm_type;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Floormark[");
		builder.append(", fm_id=");
		builder.append(this.fm_id);
		builder.append(", fm_name=");
		builder.append(this.fm_name);
		builder.append(", fm_remark=");
		builder.append(this.fm_remark);
		builder.append(", fm_sort=");
		builder.append(this.fm_sort);
		builder.append(", f_id=");
		builder.append(this.f_id);
		builder.append(", fm_type=");
		builder.append(this.fm_type);
		builder.append("]");
		return builder.toString();
	}

}


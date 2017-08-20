/*
 
 * Package:com.rbt.model
 * FileName: Catnav.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 分类信息实体
 * @author 创建人 ZMS
 * @date 创建日期 Fri Aug 14 20:22:09 CST 2015
 */
public class Catnav implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String cn_id;
	public String getCn_id() {
		return cn_id;
	}
	public void setCn_id(String cn_id) {
		this.cn_id = cn_id;
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
	private String custom_name;
	
	
	public String getCustom_name() {
		return custom_name;
	}
	public void setCustom_name(String custom_name) {
		this.custom_name = custom_name;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Catnav[");
		builder.append(", cn_id=");
		builder.append(this.cn_id);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", custom_name=");
		builder.append(this.custom_name);
		builder.append("]");
		return builder.toString();
	}

}


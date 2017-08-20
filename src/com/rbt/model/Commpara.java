/*
  
 
 * Package:com.rbt.model
 * FileName: Commpara.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Commpara implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String para_id;
	public String getPara_id() {
		return para_id;
	}
	public void setPara_id(String para_id) {
		this.para_id = para_id;
	}
	
	private String para_code;
	public String getPara_code() {
		return para_code;
	}
	public void setPara_code(String para_code) {
		this.para_code = para_code;
	}
	
	private String para_name;
	public String getPara_name() {
		return para_name;
	}
	public void setPara_name(String para_name) {
		this.para_name = para_name;
	}
	
	private String para_key;
	public String getPara_key() {
		return para_key;
	}
	public void setPara_key(String para_key) {
		this.para_key = para_key;
	}
	
	private String para_value;
	public String getPara_value() {
		return para_value;
	}
	public void setPara_value(String para_value) {
		this.para_value = para_value;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String enabled;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Commpara[");
		builder.append(", para_id=");
		builder.append(this.para_id);
		builder.append(", para_code=");
		builder.append(this.para_code);
		builder.append(", para_name=");
		builder.append(this.para_name);
		builder.append(", para_key=");
		builder.append(this.para_key);
		builder.append(", para_value=");
		builder.append(this.para_value);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", enabled=");
		builder.append(this.enabled);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append("]");
		return builder.toString();
	}

}


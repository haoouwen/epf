/*
  
 
 * Package:com.rbt.model
 * FileName: Specname.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Specname implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String spec_code;
	public String getSpec_code() {
		return spec_code;
	}
	public void setSpec_code(String spec_code) {
		this.spec_code = spec_code;
	}
	
	private String sname;
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	private String snote;
	public String getSnote() {
		return snote;
	}
	public void setSnote(String snote) {
		this.snote = snote;
	}
	
	private String salias;
	public String getSalias() {
		return salias;
	}
	public void setSalias(String salias) {
		this.salias = salias;
	}
	
	private String show_type;
	public String getShow_type() {
		return show_type;
	}
	public void setShow_type(String show_type) {
		this.show_type = show_type;
	}
	
	private String show_method;
	public String getShow_method() {
		return show_method;
	}
	public void setShow_method(String show_method) {
		this.show_method = show_method;
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
		builder.append("Specname[");
		builder.append(", spec_code=");
		builder.append(this.spec_code);
		builder.append(", sname=");
		builder.append(this.sname);
		builder.append(", snote=");
		builder.append(this.snote);
		builder.append(", salias=");
		builder.append(this.salias);
		builder.append(", show_type=");
		builder.append(this.show_type);
		builder.append(", show_method=");
		builder.append(this.show_method);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append("]");
		return builder.toString();
	}

}


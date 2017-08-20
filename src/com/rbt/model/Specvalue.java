/*
  
 
 * Package:com.rbt.model
 * FileName: Specvalue.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Specvalue implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String sv_code;
	public String getSv_code() {
		return sv_code;
	}
	public void setSv_code(String sv_code) {
		this.sv_code = sv_code;
	}
	
	private String sv_name;
	public String getSv_name() {
		return sv_name;
	}
	public void setSv_name(String sv_name) {
		this.sv_name = sv_name;
	}
	
	private String sv_img_path;
	public String getSv_img_path() {
		return sv_img_path;
	}
	public void setSv_img_path(String sv_img_path) {
		this.sv_img_path = sv_img_path;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String spec_code;
	public String getSpec_code() {
		return spec_code;
	}
	public void setSpec_code(String spec_code) {
		this.spec_code = spec_code;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Specvalue[");
		builder.append(", sv_code=");
		builder.append(this.sv_code);
		builder.append(", sv_name=");
		builder.append(this.sv_name);
		builder.append(", sv_img_path=");
		builder.append(this.sv_img_path);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", spec_code=");
		builder.append(this.spec_code);
		builder.append("]");
		return builder.toString();
	}

}


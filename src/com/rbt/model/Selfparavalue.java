/*
  
 
 * Package:com.rbt.model
 * FileName: Selfparavalue.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录自定义参数值信息实体
 * @author 创建人 LJQ
 * @date 创建日期 Sat Feb 16 14:34:05 CST 2014
 */
public class Selfparavalue implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String slef_para_value_id;
	public String getSlef_para_value_id() {
		return slef_para_value_id;
	}
	public void setSlef_para_value_id(String slef_para_value_id) {
		this.slef_para_value_id = slef_para_value_id;
	}
	
	private String slef_para_group_id;
	public String getSlef_para_group_id() {
		return slef_para_group_id;
	}
	public void setSlef_para_group_id(String slef_para_group_id) {
		this.slef_para_group_id = slef_para_group_id;
	}
	
	private String para_id;
	public String getPara_id() {
		return para_id;
	}
	public void setPara_id(String para_id) {
		this.para_id = para_id;
	}
	
	private String slef_para_value;
	public String getSlef_para_value() {
		return slef_para_value;
	}
	public void setSlef_para_value(String slef_para_value) {
		this.slef_para_value = slef_para_value;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Selfparavalue[");
		builder.append(", slef_para_value_id=");
		builder.append(this.slef_para_value_id);
		builder.append(", slef_para_group_id=");
		builder.append(this.slef_para_group_id);
		builder.append(", para_id=");
		builder.append(this.para_id);
		builder.append(", slef_para_value=");
		builder.append(this.slef_para_value);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append("]");
		return builder.toString();
	}

}


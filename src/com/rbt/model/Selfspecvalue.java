/*
  
 
 * Package:com.rbt.model
 * FileName: Selfsepcvalue.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 自定义规格值实体
 * @author 创建人 LJQ
 * @date 创建日期 Wed Jan 30 15:33:30 CST 2014
 */
public class Selfspecvalue implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String self_size_id;
	
	
	private String serial_id;
	public String getSerial_id() {
		return serial_id;
	}
	public void setSerial_id(String serial_id) {
		this.serial_id = serial_id;
	}
	
	private String self_spec_value;
	public String getSelf_spec_value() {
		return self_spec_value;
	}
	public void setSelf_spec_value(String self_spec_value) {
		this.self_spec_value = self_spec_value;
	}
	
	private String self_spec_img;
	public String getSelf_spec_img() {
		return self_spec_img;
	}
	public void setSelf_spec_img(String self_spec_img) {
		this.self_spec_img = self_spec_img;
	}
	
	private String self_img;
	public String getSelf_img() {
		return self_img;
	}
	public void setSelf_img(String self_img) {
		this.self_img = self_img;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String spec_serial_id;
	public String getSpec_serial_id() {
		return spec_serial_id;
	}
	public void setSpec_serial_id(String spec_serial_id) {
		this.spec_serial_id = spec_serial_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Selfsepcvalue[");
		builder.append(", serial_id=");
		builder.append(this.serial_id);
		builder.append(", self_spec_value=");
		builder.append(this.self_spec_value);
		builder.append(", self_spec_img=");
		builder.append(this.self_spec_img);
		builder.append(", self_img=");
		builder.append(this.self_img);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", spec_serial_id=");
		builder.append(this.spec_serial_id);
		builder.append("]");
		return builder.toString();
	}
	public String getSelf_size_id() {
		return self_size_id;
	}
	public void setSelf_size_id(String self_size_id) {
		this.self_size_id = self_size_id;
	}

}


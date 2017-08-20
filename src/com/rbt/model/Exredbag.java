/*
 * Package:com.rbt.model
 * FileName: Exredbag.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 红包兑换表实体
 * @author 创建人 XBY
 * @date 创建日期 Fri Oct 09 13:33:32 CST 2015
 */
public class Exredbag implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String ex_id;
	public String getEx_id() {
		return ex_id;
	}
	public void setEx_id(String ex_id) {
		this.ex_id = ex_id;
	}
	
	private String red_id;
	public String getRed_id() {
		return red_id;
	}
	public void setRed_id(String red_id) {
		this.red_id = red_id;
	}
	
	private String ex_state;
	public String getEx_state() {
		return ex_state;
	}
	public void setEx_state(String ex_state) {
		this.ex_state = ex_state;
	}
	
	private String red_no;
	public String getRed_no() {
		return red_no;
	}
	public void setRed_no(String red_no) {
		this.red_no = red_no;
	}
	
	private String ex_date;
	public String getEx_date() {
		return ex_date;
	}
	public void setEx_date(String ex_date) {
		this.ex_date = ex_date;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Exredbag[");
		builder.append(", ex_id=");
		builder.append(this.ex_id);
		builder.append(", red_id=");
		builder.append(this.red_id);
		builder.append(", ex_state=");
		builder.append(this.ex_state);
		builder.append(", red_no=");
		builder.append(this.red_no);
		builder.append(", ex_date=");
		builder.append(this.ex_date);
		builder.append("]");
		return builder.toString();
	}

}


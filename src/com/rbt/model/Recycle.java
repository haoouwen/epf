/*
  
 
 * Package:com.rbt.model
 * FileName: Recycle.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录回收站信息实体
 * @author 创建人 HZX
 * @date 创建日期 Mon Mar 04 15:55:48 CST 2014
 */
public class Recycle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String recycle_id;
	public String getRecycle_id() {
		return recycle_id;
	}
	public void setRecycle_id(String recycle_id) {
		this.recycle_id = recycle_id;
	}
	
	private String box_type;
	public String getBox_type() {
		return box_type;
	}
	public void setBox_type(String box_type) {
		this.box_type = box_type;
	}
	
	private String send_cust_id;
	private String get_cust_id;
	private String send_id;
	private String is_read;
	private String receive_id;

	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Recycle[");
		builder.append(", recycle_id=");
		builder.append(this.recycle_id);
		builder.append(", box_type=");
		builder.append(this.box_type);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	public String getIs_read() {
		return is_read;
	}
	public void setIs_read(String is_read) {
		this.is_read = is_read;
	}
	public String getReceive_id() {
		return receive_id;
	}
	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
	}
	public String getSend_cust_id() {
		return send_cust_id;
	}
	public void setSend_cust_id(String send_cust_id) {
		this.send_cust_id = send_cust_id;
	}
	public String getGet_cust_id() {
		return get_cust_id;
	}
	public void setGet_cust_id(String get_cust_id) {
		this.get_cust_id = get_cust_id;
	}

}


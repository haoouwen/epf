/*
 
 * Package:com.rbt.model
 * FileName: Redsumer.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 红包消费码实体
 * @author 创建人 XBY
 * @date 创建日期 Wed Aug 12 17:05:07 CST 2015
 */
public class Redsumer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String redsumer_id;
	public String getRedsumer_id() {
		return redsumer_id;
	}
	public void setRedsumer_id(String redsumer_id) {
		this.redsumer_id = redsumer_id;
	}
	
	private String red_id;
	public String getRed_id() {
		return red_id;
	}
	public void setRed_id(String red_id) {
		this.red_id = red_id;
	}
	
	private String use_state;
	public String getUse_state() {
		return use_state;
	}
	public void setUse_state(String use_state) {
		this.use_state = use_state;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String use_date;
	public String getUse_date() {
		return use_date;
	}
	public void setUse_date(String use_date) {
		this.use_date = use_date;
	}
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String redsumer_no;
	public String getRedsumer_no() {
		return redsumer_no;
	}
	public void setRedsumer_no(String redsumer_no) {
		this.redsumer_no = redsumer_no;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Redsumer[");
		builder.append(", redsumer_id=");
		builder.append(this.redsumer_id);
		builder.append(", red_id=");
		builder.append(this.red_id);
		builder.append(", use_state=");
		builder.append(this.use_state);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", use_date=");
		builder.append(this.use_date);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", redsumer_no=");
		builder.append(this.redsumer_no);
		builder.append("]");
		return builder.toString();
	}

}


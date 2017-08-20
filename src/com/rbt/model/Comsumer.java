/*
 
 * Package:com.rbt.model
 * FileName: Comsumer.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 优惠券消费码实体
 * @author 创建人 XBY
 * @date 创建日期 Wed Aug 12 14:55:34 CST 2015
 */
public class Comsumer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String comsumer_id;
	public String getComsumer_id() {
		return comsumer_id;
	}
	public void setComsumer_id(String comsumer_id) {
		this.comsumer_id = comsumer_id;
	}
	
	private String coupon_id;
	public String getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
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
	
	private String comsumer_no;
	public String getComsumer_no() {
		return comsumer_no;
	}
	public void setComsumer_no(String comsumer_no) {
		this.comsumer_no = comsumer_no;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comsumer[");
		builder.append(", comsumer_id=");
		builder.append(this.comsumer_id);
		builder.append(", coupon_id=");
		builder.append(this.coupon_id);
		builder.append(", use_state=");
		builder.append(this.use_state);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", use_date=");
		builder.append(this.use_date);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", comsumer_no=");
		builder.append(this.comsumer_no);
		builder.append("]");
		return builder.toString();
	}

}


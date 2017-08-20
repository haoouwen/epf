/*
 * Package:com.rbt.model
 * FileName: Excoupons.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 优惠券兑换表实体
 * @author 创建人 XBY
 * @date 创建日期 Fri Oct 09 13:10:41 CST 2015
 */
public class Excoupons implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String ex_id;
	public String getEx_id() {
		return ex_id;
	}
	public void setEx_id(String ex_id) {
		this.ex_id = ex_id;
	}
	
	private String coupon_id;
	public String getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	
	private String ex_state;
	public String getEx_state() {
		return ex_state;
	}
	public void setEx_state(String ex_state) {
		this.ex_state = ex_state;
	}
	
	private String coupon_no;
	public String getCoupon_no() {
		return coupon_no;
	}
	public void setCoupon_no(String coupon_no) {
		this.coupon_no = coupon_no;
	}
	
	private String ex_date;
	public String getEx_date() {
		return ex_date;
	}
	public void setEx_date(String ex_date) {
		this.ex_date = ex_date;
	}
    private String in_date;
	
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Excoupons[");
		builder.append(", ex_id=");
		builder.append(this.ex_id);
		builder.append(", coupon_id=");
		builder.append(this.coupon_id);
		builder.append(", ex_state=");
		builder.append(this.ex_state);
		builder.append(", coupon_no=");
		builder.append(this.coupon_no);
		builder.append(", ex_date=");
		builder.append(this.ex_date);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}


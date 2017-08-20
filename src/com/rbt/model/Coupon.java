/*
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Coupon.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 优惠券实体
 * @author 创建人 XBY
 * @date 创建日期 Fri Aug 07 14:37:49 CST 2015
 */
public class Coupon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String coupon_id;
	public String getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	
	private String coupon_name;
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	
	private String coupon_no;
	public String getCoupon_no() {
		return coupon_no;
	}
	public void setCoupon_no(String coupon_no) {
		this.coupon_no = coupon_no;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String coupon_type;
	public String getCoupon_type() {
		return coupon_type;
	}
	public void setCoupon_type(String coupon_type) {
		this.coupon_type = coupon_type;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String start_time;
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	
	private String end_time;
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	private String member_level;
	public String getMember_level() {
		return member_level;
	}
	public void setMember_level(String member_level) {
		this.member_level = member_level;
	}
	
	private String coupon_money;
	public String getCoupon_money() {
		return coupon_money;
	}
	public void setCoupon_money(String coupon_money) {
		this.coupon_money = coupon_money;
	}
	
	private String need_money;
	private String coupon_state;
	private String need_state;
	
	public String getNeed_money() {
		return need_money;
	}
	public void setNeed_money(String need_money) {
		this.need_money = need_money;
	}
	public String getCoupon_state() {
		return coupon_state;
	}
	public void setCoupon_state(String coupon_state) {
		this.coupon_state = coupon_state;
	}
	public String getNeed_state() {
		return need_state;
	}
	public void setNeed_state(String need_state) {
		this.need_state = need_state;
	}
	
	private String coupon_num;
	public String getCoupon_num() {
		return coupon_num;
	}
	public void setCoupon_num(String coupon_num) {
		this.coupon_num = coupon_num;
	}
	
	private String term;
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	
	private String is_show;
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coupon[");
		builder.append(", coupon_id=");
		builder.append(this.coupon_id);
		builder.append(", coupon_name=");
		builder.append(this.coupon_name);
		builder.append(", coupon_no=");
		builder.append(this.coupon_no);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", coupon_type=");
		builder.append(this.coupon_type);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", start_time=");
		builder.append(this.start_time);
		builder.append(", end_time=");
		builder.append(this.end_time);
		builder.append(", member_level=");
		builder.append(this.member_level);
		builder.append(", coupon_money=");
		builder.append(this.coupon_money);
		builder.append(", need_money=");
		builder.append(this.need_money);
		builder.append(", coupon_state=");
		builder.append(this.coupon_state);
		builder.append(", need_state=");
		builder.append(this.need_state);
		builder.append(", coupon_num=");
		builder.append(this.coupon_num);
		builder.append(", term=");
		builder.append(this.term);
		builder.append(", is_show=");
		builder.append(this.is_show);
		builder.append("]");
		return builder.toString();
	}

}


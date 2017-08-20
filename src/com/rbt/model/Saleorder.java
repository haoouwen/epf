/*
 
 * Package:com.rbt.model
 * FileName: Saleorder.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 订单促销实体
 * @author 创建人 XBY
 * @date 创建日期 Wed Aug 12 11:01:23 CST 2015
 */
public class Saleorder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String sale_id;
	public String getSale_id() {
		return sale_id;
	}
	public void setSale_id(String sale_id) {
		this.sale_id = sale_id;
	}
	
	private String sale_name;
	public String getSale_name() {
		return sale_name;
	}
	public void setSale_name(String sale_name) {
		this.sale_name = sale_name;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String priority;
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	private String is_recome;
	public String getIs_recome() {
		return is_recome;
	}
	public void setIs_recome(String is_recome) {
		this.is_recome = is_recome;
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
	
	private String term;
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	
	private String coupon_state;
	public String getCoupon_state() {
		return coupon_state;
	}
	public void setCoupon_state(String coupon_state) {
		this.coupon_state = coupon_state;
	}
	
	private String coupon_plan;
	public String getCoupon_plan() {
		return coupon_plan;
	}
	public void setCoupon_plan(String coupon_plan) {
		this.coupon_plan = coupon_plan;
	}
	
	private String term_state;
	public String getTerm_state() {
		return term_state;
	}
	public void setTerm_state(String term_state) {
		this.term_state = term_state;
	}
	
	private String platform;
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}	
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String need_money;
	public String getNeed_money() {
		return need_money;
	}
	public void setNeed_money(String need_money) {
		this.need_money = need_money;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Saleorder[");
		builder.append(", sale_id=");
		builder.append(this.sale_id);
		builder.append(", sale_name=");
		builder.append(this.sale_name);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", priority=");
		builder.append(this.priority);
		builder.append(", is_recome=");
		builder.append(this.is_recome);
		builder.append(", start_time=");
		builder.append(this.start_time);
		builder.append(", end_time=");
		builder.append(this.end_time);
		builder.append(", member_level=");
		builder.append(this.member_level);
		builder.append(", term=");
		builder.append(this.term);
		builder.append(", coupon_state=");
		builder.append(this.coupon_state);
		builder.append(", coupon_plan=");
		builder.append(this.coupon_plan);
		builder.append(", term_state=");
		builder.append(this.term_state);
		builder.append(", platform=");
		builder.append(this.platform);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", need_money=");
		builder.append(this.need_money);	
		builder.append("]");
		return builder.toString();
	}

}


/*
  
 
 * Package:com.rbt.model
 * FileName: Sellerscore.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Sellerscore implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String get_cust_id;
	public String getGet_cust_id() {
		return get_cust_id;
	}
	public void setGet_cust_id(String get_cust_id) {
		this.get_cust_id = get_cust_id;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String self_cust_id;
	public String getSelf_cust_id() {
		return self_cust_id;
	}
	public void setSelf_cust_id(String self_cust_id) {
		this.self_cust_id = self_cust_id;
	}
	
	private String desc_score;
	public String getDesc_score() {
		return desc_score;
	}
	public void setDesc_score(String desc_score) {
		this.desc_score = desc_score;
	}
	
	private String service_score;
	public String getService_score() {
		return service_score;
	}
	public void setService_score(String service_score) {
		this.service_score = service_score;
	}
	
	private String delivery_score;
	public String getDelivery_score() {
		return delivery_score;
	}
	public void setDelivery_score(String delivery_score) {
		this.delivery_score = delivery_score;
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
		builder.append("Sellerscore[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", get_cust_id=");
		builder.append(this.get_cust_id);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", self_cust_id=");
		builder.append(this.self_cust_id);
		builder.append(", desc_score=");
		builder.append(this.desc_score);
		builder.append(", service_score=");
		builder.append(this.service_score);
		builder.append(", delivery_score=");
		builder.append(this.delivery_score);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}


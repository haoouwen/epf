/*
  
 
 * Package:com.rbt.model
 * FileName: Groupladder.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 团购阶梯价格实体
 * @author 创建人 HZX
 * @date 创建日期 Mon Apr 15 17:26:40 CST 2014
 */
public class Groupladder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String group_id;
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	
	private String lownum;
	public String getLownum() {
		return lownum;
	}
	public void setLownum(String lownum) {
		this.lownum = lownum;
	}
	
	private Double price;
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Groupladder[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", group_id=");
		builder.append(this.group_id);
		builder.append(", lownum=");
		builder.append(this.lownum);
		builder.append(", price=");
		builder.append(this.price);
		builder.append("]");
		return builder.toString();
	}

}


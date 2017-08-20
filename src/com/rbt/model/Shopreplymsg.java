/*
  
 
 * Package:com.rbt.model
 * FileName: Shopreplymsg.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 店铺留言表实体
 * @author 创建人 LHY
 * @date 创建日期 Thu Feb 28 15:36:59 CST 2014
 */
public class Shopreplymsg implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String shopmsg_id;
	public String getShopmsg_id() {
		return shopmsg_id;
	}
	public void setShopmsg_id(String shopmsg_id) {
		this.shopmsg_id = shopmsg_id;
	}
	
	private String reply_in_date;
	public String getReply_in_date() {
		return reply_in_date;
	}
	public void setReply_in_date(String reply_in_date) {
		this.reply_in_date = reply_in_date;
	}
	
	private String reply_content;
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	
	private String is_enbale;
	public String getIs_enbale() {
		return is_enbale;
	}
	public void setIs_enbale(String is_enbale) {
		this.is_enbale = is_enbale;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shopreplymsg[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", shopmsg_id=");
		builder.append(this.shopmsg_id);
		builder.append(", reply_in_date=");
		builder.append(this.reply_in_date);
		builder.append(", reply_content=");
		builder.append(this.reply_content);
		builder.append(", is_enbale=");
		builder.append(this.is_enbale);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append("]");
		return builder.toString();
	}

}


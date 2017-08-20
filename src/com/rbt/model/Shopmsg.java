/*
  
 
 * Package:com.rbt.model
 * FileName: Shopmsg.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 店铺留言表实体
 * @author 创建人 LHY
 * @date 创建日期 Thu Feb 28 15:31:07 CST 2014
 */
public class Shopmsg implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String sell_cust_id;
	public String getSell_cust_id() {
		return sell_cust_id;
	}
	public void setSell_cust_id(String sell_cust_id) {
		this.sell_cust_id = sell_cust_id;
	}
	
	private String buy_cust_id;
	public String getBuy_cust_id() {
		return buy_cust_id;
	}
	public void setBuy_cust_id(String buy_cust_id) {
		this.buy_cust_id = buy_cust_id;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String msg_IP;
	public String getMsg_IP() {
		return msg_IP;
	}
	public void setMsg_IP(String msg_IP) {
		this.msg_IP = msg_IP;
	}
	
	private String msg_title;
	public String getMsg_title() {
		return msg_title;
	}
	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}
	
	private String msg_content;
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	
	private String is_enbale;
	public String getIs_enbale() {
		return is_enbale;
	}
	public void setIs_enbale(String is_enbale) {
		this.is_enbale = is_enbale;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shopmsg[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", sell_cust_id=");
		builder.append(this.sell_cust_id);
		builder.append(", buy_cust_id=");
		builder.append(this.buy_cust_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", msg_IP=");
		builder.append(this.msg_IP);
		builder.append(", msg_title=");
		builder.append(this.msg_title);
		builder.append(", msg_content=");
		builder.append(this.msg_content);
		builder.append(", is_enbale=");
		builder.append(this.is_enbale);
		builder.append("]");
		return builder.toString();
	}

}


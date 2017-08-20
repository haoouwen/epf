/*
  
 
 * Package:com.rbt.model
 * FileName: Pushlogistics.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 物流推送实体
 * @author 创建人 HZX
 * @date 创建日期 Tue Jun 18 09:31:12 CST 2014
 */
public class Pushlogistics implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String number;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	private String com;
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String is_ship;
	public String getIs_ship() {
		return is_ship;
	}
	public void setIs_ship(String is_ship) {
		this.is_ship = is_ship;
	}
	
	private String is_send;
	public String getIs_send() {
		return is_send;
	}
	public void setIs_send(String is_send) {
		this.is_send = is_send;
	}
	
	private String is_sign;
	public String getIs_sign() {
		return is_sign;
	}
	public void setIs_sign(String is_sign) {
		this.is_sign = is_sign;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pushlogistics[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", number=");
		builder.append(this.number);
		builder.append(", com=");
		builder.append(this.com);
		builder.append(", status=");
		builder.append(this.status);
		builder.append(", time=");
		builder.append(this.time);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", is_ship=");
		builder.append(this.is_ship);
		builder.append(", is_send=");
		builder.append(this.is_send);
		builder.append(", is_sign=");
		builder.append(this.is_sign);
		builder.append("]");
		return builder.toString();
	}

}


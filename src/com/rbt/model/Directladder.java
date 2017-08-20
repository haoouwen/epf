/*
  
 
 * Package:com.rbt.model
 * FileName: Directladder.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 预售商品阶梯价格实体
 * @author 创建人 HZX
 * @date 创建日期 Wed Jul 17 13:28:18 CST 2014
 */
public class Directladder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String direct_id;
	public String getDirect_id() {
		return direct_id;
	}
	public void setDirect_id(String direct_id) {
		this.direct_id = direct_id;
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
		builder.append("Directladder[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", direct_id=");
		builder.append(this.direct_id);
		builder.append(", lownum=");
		builder.append(this.lownum);
		builder.append(", price=");
		builder.append(this.price);
		builder.append("]");
		return builder.toString();
	}

}


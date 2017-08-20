/*
  
 
 * Package:com.rbt.model
 * FileName: Autoupgoods.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录商品上下架管理信息实体
 * @author 创建人 HZX
 * @date 创建日期 Fri Feb 01 10:46:02 CST 2014
 */
public class Autoupgoods implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String up_time;
	public String getUp_time() {
		return up_time;
	}
	public void setUp_time(String up_time) {
		this.up_time = up_time;
	}
	
	private String down_time;
	public String getDown_time() {
		return down_time;
	}
	public void setDown_time(String down_time) {
		this.down_time = down_time;
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
		builder.append("Autoupgoods[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", good_id=");
		builder.append(this.goods_id);
		builder.append(", up_time=");
		builder.append(this.up_time);
		builder.append(", down_time=");
		builder.append(this.down_time);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}


/*
  
 
 * Package:com.rbt.model
 * FileName: Goodsask.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Goodsask implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String c_type;
	public String getC_type() {
		return c_type;
	}
	public void setC_type(String c_type) {
		this.c_type = c_type;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String c_content;
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	
	private String c_date;
	public String getC_date() {
		return c_date;
	}
	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	
	private String re_cust_id;
	public String getRe_cust_id() {
		return re_cust_id;
	}
	public void setRe_cust_id(String re_cust_id) {
		this.re_cust_id = re_cust_id;
	}
	
	private String re_content;
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	
	private String re_date;
	public String getRe_date() {
		return re_date;
	}
	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}
	
	private String re_man;
	public String getRe_man() {
		return re_man;
	}
	public void setRe_man(String re_man) {
		this.re_man = re_man;
	}
	
	private String is_enable;
	public String getIs_enable() {
		return is_enable;
	}
	public void setIs_enable(String is_enable) {
		this.is_enable = is_enable;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goodsask[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", c_type=");
		builder.append(this.c_type);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", c_content=");
		builder.append(this.c_content);
		builder.append(", c_date=");
		builder.append(this.c_date);
		builder.append(", re_cust_id=");
		builder.append(this.re_cust_id);
		builder.append(", re_content=");
		builder.append(this.re_content);
		builder.append(", re_date=");
		builder.append(this.re_date);
		builder.append(", re_man=");
		builder.append(this.re_man);
		builder.append(", is_enable=");
		builder.append(this.is_enable);
		builder.append("]");
		return builder.toString();
	}

}


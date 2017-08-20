/*
  
 
 * Package:com.rbt.model
 * FileName: Consultation.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录商品咨询信息实体
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 28 16:47:46 CST 2014
 */
public class Consultation implements Serializable {

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
	
	private String mem_id;
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	private String c_content;
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	
	private String contact;
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	private String mem_ip;
	public String getMem_ip() {
		return mem_ip;
	}
	public void setMem_ip(String mem_ip) {
		this.mem_ip = mem_ip;
	}
	
	private String c_date;
	public String getC_date() {
		return c_date;
	}
	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	
	private String is_display;
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	}
	
	private String re_men_id;
	public String getRe_men_id() {
		return re_men_id;
	}
	public void setRe_men_id(String re_men_id) {
		this.re_men_id = re_men_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Consultation[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", c_type=");
		builder.append(this.c_type);
		builder.append(", mem_id=");
		builder.append(this.mem_id);
		builder.append(", c_content=");
		builder.append(this.c_content);
		builder.append(", contact=");
		builder.append(this.contact);
		builder.append(", mem_ip=");
		builder.append(this.mem_ip);
		builder.append(", c_date=");
		builder.append(this.c_date);
		builder.append(", is_display=");
		builder.append(this.is_display);
		builder.append(", re_men_id=");
		builder.append(this.re_men_id);
		builder.append("]");
		return builder.toString();
	}

}


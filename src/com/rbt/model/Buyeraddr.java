/*
  
 
 * Package:com.rbt.model
 * FileName: Buyeraddr.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:21 CST 2014
 */
public class Buyeraddr implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String addr_id;
	public String getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(String addr_id) {
		this.addr_id = addr_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String consignee;
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	private String post_code;
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	private String cell_phone;
	public String getCell_phone() {
		return cell_phone;
	}
	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	private String sel_address;
	public String getSel_address() {
		return sel_address;
	}
	public void setSel_address(String sel_address) {
		this.sel_address = sel_address;
	}
	
	private String identitycard;
	public String getIdentitycard() {
		return identitycard;
	}
	public void setIdentitycard(String identitycard) {
		this.identitycard = identitycard;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Buyeraddr[");
		builder.append(", addr_id=");
		builder.append(this.addr_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", consignee=");
		builder.append(this.consignee);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", address=");
		builder.append(this.address);
		builder.append(", post_code=");
		builder.append(this.post_code);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", cell_phone=");
		builder.append(this.cell_phone);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", sel_address=");
		builder.append(this.sel_address);
		builder.append(",identitycard=");
		builder.append(this.identitycard);
		builder.append("]");
		return builder.toString();
	}

}


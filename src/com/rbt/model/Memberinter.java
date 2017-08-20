/*
  
 
 * Package:com.rbt.model
 * FileName: Memberinter.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Memberinter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String fund_num;
	public String getFund_num() {
		return fund_num;
	}
	public void setFund_num(String fund_num) {
		this.fund_num = fund_num;
	}
	
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memberinter[");
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", fund_num=");
		builder.append(this.fund_num);
		builder.append("]");
		return builder.toString();
	}

}


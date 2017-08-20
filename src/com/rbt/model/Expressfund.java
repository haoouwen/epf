/*
  
 
 * Package:com.rbt.model
 * FileName: Expressfund.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Expressfund implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private Double summoney;
	public Double getSummoney() {
		return summoney;
	}
	public void setSummoney(Double summoney) {
		this.summoney = summoney;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Expressfund[");
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", summoney=");
		builder.append(this.summoney);
		builder.append("]");
		return builder.toString();
	}

}


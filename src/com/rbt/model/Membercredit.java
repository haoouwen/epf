/*
  
 
 * Package:com.rbt.model
 * FileName: Membercredit.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 会员信誉实体
 * @author 创建人 XBY
 * @date 创建日期 Tue Apr 22 19:59:28 CST 2014
 */
public class Membercredit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String credit_id;
	public String getCredit_id() {
		return credit_id;
	}
	public void setCredit_id(String credit_id) {
		this.credit_id = credit_id;
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
	
	private String buy_num;
	public String getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(String buy_num) {
		this.buy_num = buy_num;
	}
	
	private String sell_num;
	public String getSell_num() {
		return sell_num;
	}
	public void setSell_num(String sell_num) {
		this.sell_num = sell_num;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Membercredit[");
		builder.append(", credit_id=");
		builder.append(this.credit_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", buy_num=");
		builder.append(this.buy_num);
		builder.append(", sell_num=");
		builder.append(this.sell_num);
		builder.append("]");
		return builder.toString();
	}

}


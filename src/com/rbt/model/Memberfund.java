/*
  
 
 * Package:com.rbt.model
 * FileName: Memberfund.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Memberfund implements Serializable {

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
	
	private String use_num;
	public String getUse_num() {
		return use_num;
	}
	public void setUse_num(String use_num) {
		this.use_num = use_num;
	}
	
	private String freeze_num;
	public String getFreeze_num() {
		return freeze_num;
	}
	public void setFreeze_num(String _num) {
		this.freeze_num = _num;
	}
	
	private String pay_passwd;
	public String getPay_passwd() {
		return pay_passwd;
	}
	public void setPay_passwd(String pay_passwd) {
		this.pay_passwd = pay_passwd;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memberfund[");
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", fund_num=");
		builder.append(this.fund_num);
		builder.append(", use_num=");
		builder.append(this.use_num);
		builder.append(", freeze_num=");
		builder.append(this.freeze_num);
		builder.append(", pay_passwd=");
		builder.append(this.pay_passwd);
		builder.append("]");
		return builder.toString();
	}

}


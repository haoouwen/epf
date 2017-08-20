/*
  
 
 * Package:com.rbt.model
 * FileName: Receiptmanage.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录单据管理信息实体
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 30 11:13:55 CST 2014
 */
public class Receiptmanage implements Serializable {

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
	
	private String receipt_code;
	public String getReceipt_code() {
		return receipt_code;
	}
	public void setReceipt_code(String receipt_code) {
		this.receipt_code = receipt_code;
	}
	
	private String receipt_enable;
	public String getReceipt_enable() {
		return receipt_enable;
	}
	public void setReceipt_enable(String receipt_enable) {
		this.receipt_enable = receipt_enable;
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
		builder.append("Receiptmanage[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", receipt_code=");
		builder.append(this.receipt_code);
		builder.append(", receipt_content=");
		builder.append(this.receipt_enable);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}


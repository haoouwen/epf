/*
 * Package:com.rbt.model
 * FileName: Fepbill.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 代购汇账单实体
 * @author 创建人 HZX
 * @date 创建日期 Wed Sep 23 13:22:25 CST 2015
 */
public class Fepbill implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String fepbill_id;
	public String getFepbill_id() {
		return fepbill_id;
	}
	public void setFepbill_id(String fepbill_id) {
		this.fepbill_id = fepbill_id;
	}
	
	private String purchasingtotalamount;
	public String getPurchasingtotalamount() {
		return purchasingtotalamount;
	}
	public void setPurchasingtotalamount(String purchasingtotalamount) {
		this.purchasingtotalamount = purchasingtotalamount;
	}
	
	private String orderids;
	public String getOrderids() {
		return orderids;
	}
	public void setOrderids(String orderids) {
		this.orderids = orderids;
	}
	
	private String in_date;
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fepbill[");
		builder.append(", fepbill_id=");
		builder.append(this.fepbill_id);
		builder.append(", purchasingtotalamount=");
		builder.append(this.purchasingtotalamount);
		builder.append(", orderids=");
		builder.append(this.orderids);
		builder.append(", in_date=");
		builder.append(this.in_date);
		
		builder.append("]");
		return builder.toString();
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

}


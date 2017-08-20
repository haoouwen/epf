/*
  
 
 * Package:com.rbt.model
 * FileName: Receipt.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录单据模板信息实体
 * @author 创建人 HZX
 * @date 创建日期 Wed Jan 30 11:12:44 CST 2014
 */
public class Receipt implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String receipt_code;
	public String getReceipt_code() {
		return receipt_code;
	}
	public void setReceipt_code(String receipt_code) {
		this.receipt_code = receipt_code;
	}
	
	private String receipt_name;
	public String getReceipt_name() {
		return receipt_name;
	}
	public void setReceipt_name(String receipt_name) {
		this.receipt_name = receipt_name;
	}
	
	private String receipt_enable;
	public String getReceipt_enable() {
		return receipt_enable;
	}
	public void setReceipt_enable(String receipt_enable) {
		this.receipt_enable = receipt_enable;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Receipt[");
		builder.append(", receipt_code=");
		builder.append(this.receipt_code);
		builder.append(", receipt_name=");
		builder.append(this.receipt_name);
		builder.append(", receipt_enable=");
		builder.append(this.receipt_enable);
		builder.append("]");
		return builder.toString();
	}

}


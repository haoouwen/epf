/*
  
 
 * Package:com.rbt.model
 * FileName: Aftersales.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录售后维护信息实体
 * @author 创建人 HZX
 * @date 创建日期 Tue Feb 26 16:32:24 CST 2014
 */
public class Aftersales implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Aftersales[");
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", content=");
		builder.append(this.content);
		builder.append("]");
		return builder.toString();
	}

}


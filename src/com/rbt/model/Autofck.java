/*
  
 
 * Package:com.rbt.model
 * FileName: Autofck.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 在线编辑器实体
 * @author 创建人 LHY
 * @date 创建日期 Mon Jan 28 12:54:12 CST 2014
 */
public class Autofck implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String random_num;
	public String getRandom_num() {
		return random_num;
	}
	public void setRandom_num(String random_num) {
		this.random_num = random_num;
	}
	private String table_name;
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	private String table_id;
	public String getTable_id() {
		return table_id;
	}
	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Autofck[");
		builder.append(", id=");
		builder.append(this.id);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", random_num=");
		builder.append(this.random_num);
		builder.append(", table_name=");
		builder.append(this.table_name);
		builder.append(", table_id=");
		builder.append(this.table_id);
		builder.append("]");
		return builder.toString();
	}

}


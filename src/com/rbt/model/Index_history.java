/*
  
 
 * Package:com.rbt.model
 * FileName: Index_history.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录已经索引过的信息实体
 * @author 创建人 LJQ
 * @date 创建日期 Fri Aug 12 10:12:10 CST 2014
 */
public class Index_history implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String table_name;
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	
	String info_id;
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Index_history[");
		builder.append(", table_name=");
		builder.append(this.table_name);
		builder.append(", info_id=");
		builder.append(this.info_id);
		builder.append("]");
		return builder.toString();
	}

}


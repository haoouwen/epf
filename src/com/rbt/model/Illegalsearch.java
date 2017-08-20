/*
  
 
 * Package:com.rbt.model
 * FileName: Illegalsearch.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 前台搜索拦截信息实体
 * @author 创建人 HXK
 * @date 创建日期 Fri Apr 03 12:03:00 CST 2015
 */
public class Illegalsearch implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String illegal_search_id;
	public String getIllegal_search_id() {
		return illegal_search_id;
	}
	public void setIllegal_search_id(String illegal_search_id) {
		this.illegal_search_id = illegal_search_id;
	}
	
	private String illegal_search_word;
	public String getIllegal_search_word() {
		return illegal_search_word;
	}
	public void setIllegal_search_word(String illegal_search_word) {
		this.illegal_search_word = illegal_search_word;
	}
	
	private String illegal_in_date;
	public String getIllegal_in_date() {
		return illegal_in_date;
	}
	public void setIllegal_in_date(String illegal_in_date) {
		this.illegal_in_date = illegal_in_date;
	}
	
	private String illegal_ip;
	public String getIllegal_ip() {
		return illegal_ip;
	}
	public void setIllegal_ip(String illegal_ip) {
		this.illegal_ip = illegal_ip;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Illegalsearch[");
		builder.append(", illegal_search_id=");
		builder.append(this.illegal_search_id);
		builder.append(", illegal_search_word=");
		builder.append(this.illegal_search_word);
		builder.append(", illegal_in_date=");
		builder.append(this.illegal_in_date);
		builder.append(", illegal_ip=");
		builder.append(this.illegal_ip);
		builder.append("]");
		return builder.toString();
	}

}


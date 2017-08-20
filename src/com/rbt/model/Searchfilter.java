/*
  
 
 * Package:com.rbt.model
 * FileName: Searchfilter.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 前台搜索过滤规则实体
 * @author 创建人 HXK
 * @date 创建日期 Fri Apr 03 11:11:49 CST 2015
 */
public class Searchfilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String search_filter_id;
	public String getSearch_filter_id() {
		return search_filter_id;
	}
	public void setSearch_filter_id(String search_filter_id) {
		this.search_filter_id = search_filter_id;
	}
	
	private String search_in_word;
	public String getSearch_in_word() {
		return search_in_word;
	}
	public void setSearch_in_word(String search_in_word) {
		this.search_in_word = search_in_word;
	}
	
	private String search_rep_word;
	public String getSearch_rep_word() {
		return search_rep_word;
	}
	public void setSearch_rep_word(String search_rep_word) {
		this.search_rep_word = search_rep_word;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Searchfilter[");
		builder.append(", search_filter_id=");
		builder.append(this.search_filter_id);
		builder.append(", search_in_word=");
		builder.append(this.search_in_word);
		builder.append(", search_rep_word=");
		builder.append(this.search_rep_word);
		builder.append("]");
		return builder.toString();
	}

}


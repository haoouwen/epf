/*
  
 
 * Package:com.rbt.model
 * FileName: Associationkeywords.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 联想关键词实体
 * @author 创建人 HXK
 * @date 创建日期 Wed Jun 24 11:18:16 CST 2015
 */
public class Associationkeywords implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String ass_key_words_id;
	public String getAss_key_words_id() {
		return ass_key_words_id;
	}
	public void setAss_key_words_id(String ass_key_words_id) {
		this.ass_key_words_id = ass_key_words_id;
	}
	
	private String ass_key_words_title;
	public String getAss_key_words_title() {
		return ass_key_words_title;
	}
	public void setAss_key_words_title(String ass_key_words_title) {
		this.ass_key_words_title = ass_key_words_title;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private int ass_key_words_number;
	public int getAss_key_words_number() {
		return ass_key_words_number;
	}
	public void setAss_key_words_number(int ass_key_words_number) {
		this.ass_key_words_number = ass_key_words_number;
	}
	
	private String ass_key_words_show;
	public String getAss_key_words_show() {
		return ass_key_words_show;
	}
	public void setAss_key_words_show(String ass_key_words_show) {
		this.ass_key_words_show = ass_key_words_show;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	private String en_name;
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	private String sort_no;
	
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Associationkeywords[");
		builder.append(", ass_key_words_id=");
		builder.append(this.ass_key_words_id);
		builder.append(", ass_key_words_title=");
		builder.append(this.ass_key_words_title);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", ass_key_words_number=");
		builder.append(this.ass_key_words_number);
		builder.append(", ass_key_words_show=");
		builder.append(this.ass_key_words_show);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", en_name=");
		builder.append(this.en_name);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append("]");
		return builder.toString();
	}

}


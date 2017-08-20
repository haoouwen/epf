/*
  
 
 * Package:com.rbt.model
 * FileName: Seoset.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Seoset implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String seo_code;
	public String getSeo_code() {
		return seo_code;
	}
	public void setSeo_code(String seo_code) {
		this.seo_code = seo_code;
	}
	
	private String seo_title;
	public String getSeo_title() {
		return seo_title;
	}
	public void setSeo_title(String seo_title) {
		this.seo_title = seo_title;
	}
	
	private String seo_keyword;
	public String getSeo_keyword() {
		return seo_keyword;
	}
	public void setSeo_keyword(String seo_keyword) {
		this.seo_keyword = seo_keyword;
	}
	
	private String seo_decri;
	public String getSeo_decri() {
		return seo_decri;
	}
	public void setSeo_decri(String seo_decri) {
		this.seo_decri = seo_decri;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Seoset[");
		builder.append(", seo_code=");
		builder.append(this.seo_code);
		builder.append(", seo_title=");
		builder.append(this.seo_title);
		builder.append(", seo_keyword=");
		builder.append(this.seo_keyword);
		builder.append(", seo_decri=");
		builder.append(this.seo_decri);
		builder.append("]");
		return builder.toString();
	}

}


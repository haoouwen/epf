/*
  
 
 * Package:com.rbt.model
 * FileName: Article.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录文章管理表信息实体
 * @author 创建人 HZX
 * @date 创建日期 Wed Feb 20 10:44:52 CST 2014
 */
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String article_id;
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private String art_source;
	public String getArt_source() {
		return art_source;
	}
	public void setArt_source(String art_source) {
		this.art_source = art_source;
	}
	
	private String art_author_;
	public String getArt_author_() {
		return art_author_;
	}
	public void setArt_author_(String art_author_) {
		this.art_author_ = art_author_;
	}
	
	private String out_link;
	public String getOut_link() {
		return out_link;
	}
	public void setOut_link(String out_link) {
		this.out_link = out_link;
	}
	
	private String is_sticky;
	public String getIs_sticky() {
		return is_sticky;
	}
	public void setIs_sticky(String is_sticky) {
		this.is_sticky = is_sticky;
	}
	
	private String is_display;
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
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
	
	private String seo_descri;
	public String getSeo_descri() {
		return seo_descri;
	}
	public void setSeo_descri(String seo_descri) {
		this.seo_descri = seo_descri;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
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
		builder.append("Article[");
		builder.append(", article_id=");
		builder.append(this.article_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", art_source=");
		builder.append(this.art_source);
		builder.append(", art_author_=");
		builder.append(this.art_author_);
		builder.append(", out_link=");
		builder.append(this.out_link);
		builder.append(", is_sticky=");
		builder.append(this.is_sticky);
		builder.append(", is_display=");
		builder.append(this.is_display);
		builder.append(", seo_title=");
		builder.append(this.seo_title);
		builder.append(", seo_keyword=");
		builder.append(this.seo_keyword);
		builder.append(", seo_descri=");
		builder.append(this.seo_descri);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}


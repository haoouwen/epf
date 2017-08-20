/*
  
 
 * Package:com.rbt.model
 * FileName: Goodsbrand.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Goodsbrand implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String brand_id;
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	
	private String brand_name;
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	
	private String en_name;
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	
	private String en_index;
	public String getEn_index() {
		return en_index;
	}
	public void setEn_index(String en_index) {
		this.en_index = en_index;
	}
	
	private String brand_site;
	public String getBrand_site() {
		return brand_site;
	}
	public void setBrand_site(String brand_site) {
		this.brand_site = brand_site;
	}
	
	private String logo;
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String goods_attr;
	public String getGoods_attr() {
		return goods_attr;
	}
	public void setGoods_attr(String goods_attr) {
		this.goods_attr = goods_attr;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String is_recom;
	public String getIs_recom() {
		return is_recom;
	}
	public void setIs_recom(String is_recom) {
		this.is_recom = is_recom;
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
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goodsbrand[");
		builder.append(", brand_id=");
		builder.append(this.brand_id);
		builder.append(", brand_name=");
		builder.append(this.brand_name);
		builder.append(", en_name=");
		builder.append(this.en_name);
		builder.append(", en_index=");
		builder.append(this.en_index);
		builder.append(", brand_site=");
		builder.append(this.brand_site);
		builder.append(", logo=");
		builder.append(this.logo);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", goods_attr=");
		builder.append(this.goods_attr);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", is_recom=");
		builder.append(this.is_recom);
		builder.append(", seo_title=");
		builder.append(this.seo_title);
		builder.append(", seo_keyword=");
		builder.append(this.seo_keyword);
		builder.append(", seo_descri=");
		builder.append(this.seo_descri);
		builder.append("]");
		return builder.toString();
	}

}


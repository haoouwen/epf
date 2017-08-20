/*
  
 
 * Package:com.rbt.model
 * FileName: Collect.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;

import com.hp.hpl.sparta.xpath.ThisNodeTest;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:21 CST 2014
 */
public class Collect implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String coll_id;
	public String getColl_id() {
		return coll_id;
	}
	public void setColl_id(String coll_id) {
		this.coll_id = coll_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	private String link_url;
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	private String coll_type;
	public String getColl_type() {
		return coll_type;
	}
	public void setColl_type(String coll_type) {
		this.coll_type = coll_type;
	}
	
	private String brand_id;
	
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Collect[");
		builder.append(", coll_id=");
		builder.append(this.coll_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", link_url=");
		builder.append(this.link_url);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", coll_type=");
		builder.append(this.coll_type);
		builder.append(", brand_id=");
		builder.append(this.brand_id);
		builder.append("]");
		return builder.toString();
	}

}


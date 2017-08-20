/*
 
 * Package:com.rbt.model
 * FileName: Goodfloormark.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商品楼层信息实体
 * @author 创建人 HXK
 * @date 创建日期 Mon Aug 10 11:00:14 CST 2015
 */
public class Goodfloormark implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String gm_id;
	public String getGm_id() {
		return gm_id;
	}
	public void setGm_id(String gm_id) {
		this.gm_id = gm_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String fm_id;
	public String getFm_id() {
		return fm_id;
	}
	public void setFm_id(String fm_id) {
		this.fm_id = fm_id;
	}
	
	private String f_id;
	public String getF_id() {
		return f_id;
	}
	public void setF_id(String f_id) {
		this.f_id = f_id;
	}
	
	private String gm_name;
	public String getGm_name() {
		return gm_name;
	}
	public void setGm_name(String gm_name) {
		this.gm_name = gm_name;
	}
	
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	private String gm_url;
	public String getGm_url() {
		return gm_url;
	}
	public void setGm_url(String gm_url) {
		this.gm_url = gm_url;
	}
	
	private String gm_type;
	public String getGm_type() {
		return gm_type;
	}
	public void setGm_type(String gm_type) {
		this.gm_type = gm_type;
	}
	
	private String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	private String gm_sort;
	public String getGm_sort() {
		return gm_sort;
	}
	public void setGm_sort(String gm_sort) {
		this.gm_sort = gm_sort;
	}
	
	private String gm_position;
	public String getGm_position() {
		return gm_position;
	}
	public void setGm_position(String gm_position) {
		this.gm_position = gm_position;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goodfloormark[");
		builder.append(", gm_id=");
		builder.append(this.gm_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", fm_id=");
		builder.append(this.fm_id);
		builder.append(", f_id=");
		builder.append(this.f_id);
		builder.append(", gm_name=");
		builder.append(this.gm_name);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", gm_url=");
		builder.append(this.gm_url);
		builder.append(", gm_type=");
		builder.append(this.gm_type);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", gm_sort=");
		builder.append(this.gm_sort);
		builder.append(", gm_position=");
		builder.append(this.gm_position);
		builder.append("]");
		return builder.toString();
	}

}


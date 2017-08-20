/*
  
 
 * Package:com.rbt.model
 * FileName: Link.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Link implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String link_id;
	public String getLink_id() {
		return link_id;
	}
	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}
	
	private String link_name;
	public String getLink_name() {
		return link_name;
	}
	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}
	
	private String link_group;
	public String getLink_group() {
		return link_group;
	}
	public void setLink_group(String link_group) {
		this.link_group = link_group;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String is_display;
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	}
	
	private String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Link[");
		builder.append(", link_id=");
		builder.append(this.link_id);
		builder.append(", link_name=");
		builder.append(this.link_name);
		builder.append(", link_group=");
		builder.append(this.link_group);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", url=");
		builder.append(this.url);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", is_display=");
		builder.append(this.is_display);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append("]");
		return builder.toString();
	}

}


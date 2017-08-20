/*
  
 
 * Package:com.rbt.model
 * FileName: Nav.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Nav implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String nav_id;
	public String getNav_id() {
		return nav_id;
	}
	public void setNav_id(String nav_id) {
		this.nav_id = nav_id;
	}
	
	private String nav_name;
	public String getNav_name() {
		return nav_name;
	}
	public void setNav_name(String nav_name) {
		this.nav_name = nav_name;
	}
	
	private String isshow;
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String isopen;
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
	
	private String link_url;
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	
	private String nav_post;
	public String getNav_post() {
		return nav_post;
	}
	public void setNav_post(String nav_post) {
		this.nav_post = nav_post;
	}
	
	private String nav_code;
	public String getNav_code() {
		return nav_code;
	}
	public void setNav_code(String nav_code) {
		this.nav_code = nav_code;
	}
    //小图标
	private  String nav_icon;
	
	public String getNav_icon() {
		return nav_icon;
	}
	public void setNav_icon(String nav_icon) {
		this.nav_icon = nav_icon;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nav[");
		builder.append(", nav_id=");
		builder.append(this.nav_id);
		builder.append(", nav_name=");
		builder.append(this.nav_name);
		builder.append(", isshow=");
		builder.append(this.isshow);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", isopen=");
		builder.append(this.isopen);
		builder.append(", link_url=");
		builder.append(this.link_url);
		builder.append(", nav_post=");
		builder.append(this.nav_post);
		builder.append(", nav_code=");
		builder.append(this.nav_code);
		builder.append(", nav_icon=");
		builder.append(this.nav_icon);
		builder.append("]");
		return builder.toString();
	}

}


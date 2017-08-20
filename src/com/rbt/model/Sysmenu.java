/*
  
 
 * Package:com.rbt.model
 * FileName: Sysmenu.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Sysmenu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String menu_id;
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	
	private String menu_name;
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	
	private String syscode;
	public String getSyscode() {
		return syscode;
	}
	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}
	
	private String up_menu_id;
	public String getUp_menu_id() {
		return up_menu_id;
	}
	public void setUp_menu_id(String up_menu_id) {
		this.up_menu_id = up_menu_id;
	}
	
	private String menu_level;
	public String getMenu_level() {
		return menu_level;
	}
	public void setMenu_level(String menu_level) {
		this.menu_level = menu_level;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String enabled;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	private String target;
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sysmenu[");
		builder.append(", menu_id=");
		builder.append(this.menu_id);
		builder.append(", menu_name=");
		builder.append(this.menu_name);
		builder.append(", syscode=");
		builder.append(this.syscode);
		builder.append(", up_menu_id=");
		builder.append(this.up_menu_id);
		builder.append(", menu_level=");
		builder.append(this.menu_level);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", enabled=");
		builder.append(this.enabled);
		builder.append(", url=");
		builder.append(this.url);
		builder.append(", target=");
		builder.append(this.target);
		builder.append("]");
		return builder.toString();
	}

}


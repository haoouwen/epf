/*
  
 
 * Package:com.rbt.model
 * FileName: Sysmodule.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Sysmodule implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String module_type;
	public String getModule_type() {
		return module_type;
	}
	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	
	private String module_name;
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	
	private String table_name;
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	
	private String mod_type;
	public String getMod_type() {
		return mod_type;
	}
	public void setMod_type(String mod_type) {
		this.mod_type = mod_type;
	}
	
	private String state_code;
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	
	private String is_catattr;
	public String getIs_catattr() {
		return is_catattr;
	}
	public void setIs_catattr(String is_catattr) {
		this.is_catattr = is_catattr;
	}
	
	private String install_dir;
	public String getInstall_dir() {
		return install_dir;
	}
	public void setInstall_dir(String install_dir) {
		this.install_dir = install_dir;
	}
	
	private String link_menu;
	public String getLink_menu() {
		return link_menu;
	}
	public void setLink_menu(String link_menu) {
		this.link_menu = link_menu;
	}
	
	private String link_table;
	public String getLink_table() {
		return link_table;
	}
	public void setLink_table(String link_table) {
		this.link_table = link_table;
	}
	
	private String link_file;
	public String getLink_file() {
		return link_file;
	}
	public void setLink_file(String link_file) {
		this.link_file = link_file;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String is_mem;
	public String getIs_mem() {
		return is_mem;
	}
	public void setIs_mem(String is_mem) {
		this.is_mem = is_mem;
	}
	
	private String moudleinfo_url;
	public String getMoudleinfo_url() {
		return moudleinfo_url;
	}
	public void setMoudleinfo_url(String moudleinfo_url) {
		this.moudleinfo_url = moudleinfo_url;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sysmodule[");
		builder.append(", module_type=");
		builder.append(this.module_type);
		builder.append(", module_name=");
		builder.append(this.module_name);
		builder.append(", table_name=");
		builder.append(this.table_name);
		builder.append(", mod_type=");
		builder.append(this.mod_type);
		builder.append(", state_code=");
		builder.append(this.state_code);
		builder.append(", is_catattr=");
		builder.append(this.is_catattr);
		builder.append(", install_dir=");
		builder.append(this.install_dir);
		builder.append(", link_menu=");
		builder.append(this.link_menu);
		builder.append(", link_table=");
		builder.append(this.link_table);
		builder.append(", link_file=");
		builder.append(this.link_file);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", is_mem=");
		builder.append(this.is_mem);
		builder.append(", moudleinfo_url=");
		builder.append(this.moudleinfo_url);
		builder.append("]");
		return builder.toString();
	}

}


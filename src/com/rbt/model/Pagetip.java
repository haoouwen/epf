/*
  
 
 * Package:com.rbt.model
 * FileName: Pagetip.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录页面显示管理信息实体
 * @author 创建人 HZX
 * @date 创建日期 Tue Jan 29 13:10:46 CST 2014
 */
public class Pagetip implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String page_code;
	public String getPage_code() {
		return page_code;
	}
	public void setPage_code(String page_code) {
		this.page_code = page_code;
	}
	
	private String page_content;
	public String getPage_content() {
		return page_content;
	}
	public void setPage_content(String page_content) {
		this.page_content = page_content;
	}
	
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pagetip[");
		builder.append(", page_code=");
		builder.append(this.page_code);
		builder.append(", page_content=");
		builder.append(this.page_content);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append(", state=");
		builder.append(this.state);
		builder.append("]");
		return builder.toString();
	}

}


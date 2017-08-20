/*
  
 
 * Package:com.rbt.model
 * FileName: Interrule.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Interrule implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String rule_id;
	public String getRule_id() {
		return rule_id;
	}
	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}
	
	private String rule_code;
	public String getRule_code() {
		return rule_code;
	}
	public void setRule_code(String rule_code) {
		this.rule_code = rule_code;
	}
	
	private String rule_name;
	public String getRule_name() {
		return rule_name;
	}
	public void setRule_name(String rule_name) {
		this.rule_name = rule_name;
	}
	
	private String internum;
	public String getInternum() {
		return internum;
	}
	public void setInternum(String internum) {
		this.internum = internum;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Interrule[");
		builder.append(", rule_id=");
		builder.append(this.rule_id);
		builder.append(", rule_code=");
		builder.append(this.rule_code);
		builder.append(", rule_name=");
		builder.append(this.rule_name);
		builder.append(", internum=");
		builder.append(this.internum);
		builder.append("]");
		return builder.toString();
	}

}


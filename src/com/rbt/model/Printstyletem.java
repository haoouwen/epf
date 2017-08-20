/*
  
 
 * Package:com.rbt.model
 * FileName: Printstyletem.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录打印样式模板信息实体
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 28 10:45:36 CST 2014
 */
public class Printstyletem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String template_code;
	public String getTemplate_code() {
		return template_code;
	}
	public void setTemplate_code(String template_code) {
		this.template_code = template_code;
	}
	
	private String template_name;
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	
	private String label_explan;
	public String getLabel_explan() {
		return label_explan;
	}
	public void setLabel_explan(String label_explan) {
		this.label_explan = label_explan;
	}
	
	private String print_content;
	public String getPrint_content() {
		return print_content;
	}
	public void setPrint_content(String print_content) {
		this.print_content = print_content;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Printstyletem[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", template_code=");
		builder.append(this.template_code);
		builder.append(", template_name=");
		builder.append(this.template_name);
		builder.append(", label_explan=");
		builder.append(this.label_explan);
		builder.append(", print_content=");
		builder.append(this.print_content);
		builder.append("]");
		return builder.toString();
	}

}


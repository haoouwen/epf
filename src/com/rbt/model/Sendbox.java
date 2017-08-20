/*
  
 
 * Package:com.rbt.model
 * FileName: Sendbox.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录发件箱信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 30 15:36:28 CST 2014
 */
public class Sendbox implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String send_id;
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	
	private String send_cust_id;
	public String getSend_cust_id() {
		return send_cust_id;
	}
	public void setSend_cust_id(String send_cust_id) {
		this.send_cust_id = send_cust_id;
	}
	
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String is_send_del;
	public String getIs_send_del() {
		return is_send_del;
	}
	public void setIs_send_del(String is_send_del) {
		this.is_send_del = is_send_del;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public String recevie_name;
	
	private String is_draft;
	public String getIs_draft() {
		return is_draft;
	}
	public void setIs_draft(String is_draft) {
		this.is_draft = is_draft;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sendbox[");
		builder.append(", send_id=");
		builder.append(this.send_id);
		builder.append(", send_cust_id=");
		builder.append(this.send_cust_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", is_send_del=");
		builder.append(this.is_send_del);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", recevie_name=");
		builder.append(this.recevie_name);
		builder.append(", is_draft=");
		builder.append(this.is_draft);
		builder.append("]");
		return builder.toString();
	}
	public String getRecevie_name() {
		return recevie_name;
	}
	public void setRecevie_name(String recevie_name) {
		this.recevie_name = recevie_name;
	}

}


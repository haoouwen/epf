/*
  
 
 * Package:com.rbt.model
 * FileName: Receivebox.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录收件箱信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Wed Jan 30 15:37:25 CST 2014
 */
public class Receivebox implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String receive_id;
	public String getReceive_id() {
		return receive_id;
	}
	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
	}
	
	private String send_id;
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	
	private String get_cust_id;
	public String getGet_cust_id() {
		return get_cust_id;
	}
	public void setGet_cust_id(String get_cust_id) {
		this.get_cust_id = get_cust_id;
	}
	
	private String is_read;
	public String getIs_read() {
		return is_read;
	}
	public void setIs_read(String is_read) {
		this.is_read = is_read;
	}
	
	private String is_get_del;
	public String getIs_get_del() {
		return is_get_del;
	}
	public void setIs_get_del(String is_get_del) {
		this.is_get_del = is_get_del;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Receivebox[");
		builder.append(", receive_id=");
		builder.append(this.receive_id);
		builder.append(", send_id=");
		builder.append(this.send_id);
		builder.append(", get_cust_id=");
		builder.append(this.get_cust_id);
		builder.append(", is_read=");
		builder.append(this.is_read);
		builder.append(", is_get_del=");
		builder.append(this.is_get_del);
		builder.append("]");
		return builder.toString();
	}

}


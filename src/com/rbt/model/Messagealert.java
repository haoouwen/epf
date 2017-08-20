/*
  
 
 * Package:com.rbt.model
 * FileName: Messagealert.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录信息提醒设置信息实体
 * @author 创建人 HZX
 * @date 创建日期 Sat Feb 02 10:29:52 CST 2014
 */
public class Messagealert implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String msg_code;
	public String getMsg_code() {
		return msg_code;
	}
	public void setMsg_code(String msg_code) {
		this.msg_code = msg_code;
	}
	
	private String msg_title;
	public String getMsg_title() {
		return msg_title;
	}
	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}
	
	private String is_send_email;
	public String getIs_send_email() {
		return is_send_email;
	}
	public void setIs_send_email(String is_send_email) {
		this.is_send_email = is_send_email;
	}
	
	private String email_code;
	public String getEmail_code() {
		return email_code;
	}
	public void setEmail_code(String email_code) {
		this.email_code = email_code;
	}
	
	private String is_send_mobile;
	public String getIs_send_mobile() {
		return is_send_mobile;
	}
	public void setIs_send_mobile(String is_send_mobile) {
		this.is_send_mobile = is_send_mobile;
	}
	
	private String mobile_code;
	public String getMobile_code() {
		return mobile_code;
	}
	public void setMobile_code(String mobile_code) {
		this.mobile_code = mobile_code;
	}
	
	private String is_send_letter;
	public String getIs_send_letter() {
		return is_send_letter;
	}
	public void setIs_send_letter(String is_send_letter) {
		this.is_send_letter = is_send_letter;
	}
	
	private String letter_code;
	public String getLetter_code() {
		return letter_code;
	}
	public void setLetter_code(String letter_code) {
		this.letter_code = letter_code;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Messagealert[");
		builder.append(", msg_code=");
		builder.append(this.msg_code);
		builder.append(", msg_title=");
		builder.append(this.msg_title);
		builder.append(", is_send_email=");
		builder.append(this.is_send_email);
		builder.append(", email_code=");
		builder.append(this.email_code);
		builder.append(", is_send_mobile=");
		builder.append(this.is_send_mobile);
		builder.append(", mobile_code=");
		builder.append(this.mobile_code);
		builder.append(", is_send_letter=");
		builder.append(this.is_send_letter);
		builder.append(", letter_code=");
		builder.append(this.letter_code);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append("]");
		return builder.toString();
	}

}


/*
  
 
 * Package:com.rbt.model
 * FileName: Memprotect.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 记录会员密保信息实体
 * @author 创建人 HZX
 * @date 创建日期 Thu Feb 21 09:21:06 CST 2014
 */
public class Memprotect implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String question;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	private String answer;
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memprotect[");
		builder.append(", id=");
		builder.append(this.id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", question=");
		builder.append(this.question);
		builder.append(", answer=");
		builder.append(this.answer);
		builder.append("]");
		return builder.toString();
	}

}


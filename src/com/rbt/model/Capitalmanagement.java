/*
  
 
 * Package:com.rbt.model
 * FileName: Capitalmanagement.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 运营商资金管理实体
 * @author 创建人 HZX
 * @date 创建日期 Fri Aug 23 14:17:23 CST 2014
 */
public class Capitalmanagement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String pass_men;
	public String getPass_men() {
		return pass_men;
	}
	public void setPass_men(String pass_men) {
		this.pass_men = pass_men;
	}
	
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
		builder.append("Capitalmanagement[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", pass_men=");
		builder.append(this.pass_men);
		builder.append(", password=");
		builder.append(this.password);
		builder.append(", question=");
		builder.append(this.question);
		builder.append(", answer=");
		builder.append(this.answer);
		builder.append("]");
		return builder.toString();
	}

}


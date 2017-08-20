/*
 * Package:com.rbt.model
 * FileName: TokensInfo.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 IOSAndroidToken实体
 * @author 创建人 HXK
 * @date 创建日期 Wed Jul 06 12:11:35 CST 2016
 */
public class TokensInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String tok_id;
	public String getTok_id() {
		return tok_id;
	}
	public void setTok_id(String tok_id) {
		this.tok_id = tok_id;
	}
	
	private String token_name;
	public String getToken_name() {
		return token_name;
	}
	public void setToken_name(String token_name) {
		this.token_name = token_name;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	private String  token_type;
	
	
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TokensInfo[");
		builder.append(", tok_id=");
		builder.append(this.tok_id);
		builder.append(", token_name=");
		builder.append(this.token_name);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", token_type=");
		builder.append(this.token_type);
		builder.append("]");
		return builder.toString();
	}

}


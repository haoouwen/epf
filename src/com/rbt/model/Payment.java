/*
  
 
 * Package:com.rbt.model
 * FileName: Payment.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商城后台角色信息实体
 * @author 创建人 LSQ
 * @date 创建日期 Mon Jan 28 10:52:22 CST 2014
 */
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String pay_id;
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	
	private String pay_code;
	public String getPay_code() {
		return pay_code;
	}
	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}
	
	private String pay_name;
	public String getPay_name() {
		return pay_name;
	}
	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}
	
	private String pay_desc;
	public String getPay_desc() {
		return pay_desc;
	}
	public void setPay_desc(String pay_desc) {
		this.pay_desc = pay_desc;
	}
	
	private String seller_name;
	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	
	private String pay_account;
	public String getPay_account() {
		return pay_account;
	}
	public void setPay_account(String pay_account) {
		this.pay_account = pay_account;
	}
	
	private String passwd;
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	private String hand_fare;
	public String getHand_fare() {
		return hand_fare;
	}
	public void setHand_fare(String hand_fare) {
		this.hand_fare = hand_fare;
	}
	
	private String enabled;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	private String pay_logo;
	public String getPay_logo() {
		return pay_logo;
	}
	public void setPay_logo(String pay_logo) {
		this.pay_logo = pay_logo;
	}
	
	//应用ID
	private String appID;
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	
	//应用密钥
	private String appSecret;
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Payment[");
		builder.append(", pay_id=");
		builder.append(this.pay_id);
		builder.append(", pay_code=");
		builder.append(this.pay_code);
		builder.append(", pay_name=");
		builder.append(this.pay_name);
		builder.append(", pay_desc=");
		builder.append(this.pay_desc);
		builder.append(", seller_name=");
		builder.append(this.seller_name);
		builder.append(", pay_account=");
		builder.append(this.pay_account);
		builder.append(", passwd=");
		builder.append(this.passwd);
		builder.append(", hand_fare=");
		builder.append(this.hand_fare);
		builder.append(", enabled=");
		builder.append(this.enabled);
		builder.append(", pay_logo=");
		builder.append(this.pay_logo);
		builder.append(", appID=");
		builder.append(this.appID);
		builder.append(", appSecret=");
		builder.append(this.appSecret);
		builder.append("]");
		return builder.toString();
	}

}


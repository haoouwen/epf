/*
  
 
 * Package:com.rbt.model
 * FileName: Memberfundrun.java 
 */
package com.rbt.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @function 功能 审核余额调整实体
 * @author 创建人 XBY
 * @date 创建日期 Mon Jun 09 15:36:58 CST 2014
 */
public class Memberfundrun implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String fund_id;
	public String getFund_id() {
		return fund_id;
	}
	public void setFund_id(String fund_id) {
		this.fund_id = fund_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private Double fund_num;
	public Double getFund_num() {
		return fund_num;
	}
	public void setFund_num(Double fund_num) {
		this.fund_num = fund_num;
	}
	
	private String fund_type;
	public String getFund_type() {
		return fund_type;
	}
	public void setFund_type(String fund_type) {
		this.fund_type = fund_type;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String reason;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	private String auditreason;
	public String getAuditreason() {
		return auditreason;
	}
	public void setAuditreason(String auditreason) {
		this.auditreason = auditreason;
	}
	
    private String user_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	private String audit_user_name;
	private String apply_user_name;
	
	public String getAudit_user_name() {
		return audit_user_name;
	}
	public void setAudit_user_name(String audit_user_name) {
		this.audit_user_name = audit_user_name;
	}
	public String getApply_user_name() {
		return apply_user_name;
	}
	public void setApply_user_name(String apply_user_name) {
		this.apply_user_name = apply_user_name;
	}
	private String apply_date;
	private String audit_date;
	
	
	public String getApply_date() {
		return apply_date;
	}
	public void setApply_date(String apply_date) {
		this.apply_date = apply_date;
	}
	public String getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(String audit_date) {
		this.audit_date = audit_date;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memberfundrun[");
		builder.append(", fund_id=");
		builder.append(this.fund_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", fund_num=");
		builder.append(this.fund_num);
		builder.append(", fund_type=");
		builder.append(this.fund_type);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", reason=");
		builder.append(this.reason);
		builder.append(", auditreason=");
		builder.append(this.auditreason);
		builder.append(", user_name=");
		builder.append(this.user_name);
		builder.append(", audit_user_name=");
		builder.append(this.audit_user_name);
		builder.append(", apply_user_name=");
		builder.append(this.apply_user_name);
		builder.append(", apply_date=");
		builder.append(this.apply_date);
		builder.append(", audit_date=");
		builder.append(this.audit_date);
		builder.append("]");
		return builder.toString();
	}

}

